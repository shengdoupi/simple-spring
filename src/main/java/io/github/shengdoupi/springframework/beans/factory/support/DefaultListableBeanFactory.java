package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Default listable bean factory.
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    
    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("Bean definition null exception.");
        }
        return beanDefinitionMap.get(beanName);
    }
    
    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.forEach((name, beanDefinition) -> {
            if (beanDefinition.isSingleton() && !beanDefinition.isLazyInit()) {
                getBean(name);
            }
        });
    }
    
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
    
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }
    
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> resultMap = new HashMap<>();
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            Class clazz = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(clazz)) {
                T bean = (T) getBean(beanName);
                resultMap.put(beanName, bean);
            }
        }
        return resultMap;
    }
    
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<T> beans = new ArrayList<>();
        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (requiredType.isAssignableFrom(beanDefinition.getBeanClass())) {
                beans.add((T) getBean(beanName));
            }
        }
        if (beans.size() == 1) {
            return beans.get(0);
        }
        throw new BeansException("More than one instance with type same as the requiredType");
    }
    
    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNameSet = beanDefinitionMap.keySet();
        return beanNameSet.toArray(new String[beanNameSet.size()]);
    }
}
