package io.github.shengdoupi.springframework.beans.factory.config;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.ListableBeanFactory;

/**
 * @author zhoukehh
 * @date 2024/4/26
 * @description Configuration interface to be implemented by most listable bean factories.
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    
    /**
     * Returns the registered bean definition given a specific bean name.
     * @param beanName
     * @return BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName);
    
    
    /**
     * Ensure all singleton bean has been instantiated, typically invoked at the end of factory setup.
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

}
