package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.core.io.Resource;
import io.github.shengdoupi.springframework.core.io.ResourceLoader;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description
 */
public interface BeanDefinitionReader {
    
    /**
     * Get bean definition registry.
     * @return Bean definition registry.
     */
    BeanDefinitionRegistry getRegistry();
    
    /**
     * Get resource loader.
     * @return Resource loader.
     */
    ResourceLoader getResourceLoader();
    
    /**
     * Load bean definitions.
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;
    
    /**
     * Load bean definitions.
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    
    /**
     * Load bean definitions.
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;
    
    /**
     * Load bean definitions.
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}
