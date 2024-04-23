package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.core.io.ResourceLoader;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description Abstract bean definition reader.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    private final BeanDefinitionRegistry registry;
    
    private ResourceLoader resourceLoader;
    
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }
    
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }
    
    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
    
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
