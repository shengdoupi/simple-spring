package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zhoukehh
 * @date 2024/3/27
 * @description
 */
public interface BeanDefinitionRegistry {
    
    /**
     * Register bean definition.
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    
    /**
     * Contains bean definition.
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
