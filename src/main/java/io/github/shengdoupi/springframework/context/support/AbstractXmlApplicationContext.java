package io.github.shengdoupi.springframework.context.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.support.DefaultListableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.support.XmlBeanDefinitionReader;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
    }
    
    /**
     * Get bean definition config locations.
     * @return
     */
    protected abstract String[] getConfigLocations();
}
