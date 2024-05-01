package io.github.shengdoupi.springframework.beans.factory.support;

import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import com.sun.xml.internal.ws.util.xml.XmlUtil;
import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.PropertyValue;
import io.github.shengdoupi.springframework.beans.PropertyValues;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.config.BeanReference;
import io.github.shengdoupi.springframework.core.io.Resource;
import io.github.shengdoupi.springframework.core.io.ResourceLoader;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description Xml bean definition reader.
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }
    
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }
    
    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            doLoadBeanDefinitions(resource.getInputStream(), resource);
        } catch (IOException e) {
            throw new BeansException("Get input stream error", e);
        }
    }
    
    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }
    
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }
    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
    
    
    private void doLoadBeanDefinitions(InputStream inputStream, Resource resource) {
        try {
            // parse doc
            Document doc = XMLUtils.read(inputStream);
            Element root = doc.getDocumentElement();
            NodeList childNodes = root.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); ++i) {
                if (!(childNodes.item(i) instanceof Element)) {
                    continue;
                }
                if (!"bean".equals(childNodes.item(i).getNodeName())) {
                    continue;
                }
                // parse element
                Element bean = (Element) childNodes.item(i);
                String id = bean.getAttribute("id");
                String name = bean.getAttribute("name");
                String className = bean.getAttribute("class");
                String initMethod = bean.getAttribute("init-method");
                String destroyMethod = bean.getAttribute("destroy-method");
                // get clazz
                Class<?> clazz = Class.forName(className);
                // beanName
                String beanName = StringUtils.isNotBlank(id) ? id : name;
                if (StringUtils.isBlank(beanName)) {
                    beanName = clazz.getSimpleName();
                }
                // BeanDefinition
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                NodeList beanChildNodes = bean.getChildNodes();
                PropertyValues propertyValues = new PropertyValues();
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setInitMethodName(initMethod);
                beanDefinition.setDestroyMethodName(destroyMethod);
                for (int j = 0; j < beanChildNodes.getLength(); ++j) {
                    if (!(beanChildNodes.item(j) instanceof Element)) {
                        continue;
                    }
                    if (!"property".equals(beanChildNodes.item(j).getNodeName())) {
                        continue;
                    }
                    // parse property
                    Element property = (Element) beanChildNodes.item(j);
                    String attrName = property.getAttribute("name");
                    String attrValue = property.getAttribute("value");
                    String attrRef = property.getAttribute("ref");
                    Object value = StringUtils.isNotBlank(attrRef) ? (BeanReference) () -> attrRef : attrValue;
                    propertyValues.addPropertyValue(new PropertyValue(attrName, value));
                }
                if (getRegistry().containsBeanDefinition(beanName)) {
                    throw new BeansException("Duplicate bean name: " + beanName);
                }
                // Register bean definition.
                getRegistry().registerBeanDefinition(beanName, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeansException("Load bean definition error", e);
        }
        
    }
}
