package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Default listable bean factory.
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
    
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
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
