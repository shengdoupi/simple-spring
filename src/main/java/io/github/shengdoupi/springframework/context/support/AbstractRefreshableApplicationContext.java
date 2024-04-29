package io.github.shengdoupi.springframework.context.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.io.IOException;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    private volatile DefaultListableBeanFactory beanFactory;
    
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }
    
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
    
    /**
     * Load bean definitions into the given bean factory, typically through
     * delegating to one or more bean definition readers.
     *
     * @param beanFactory
     * @throws BeansException
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;
    
    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
    
}
