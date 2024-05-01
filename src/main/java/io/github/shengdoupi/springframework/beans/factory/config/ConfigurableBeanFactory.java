package io.github.shengdoupi.springframework.beans.factory.config;

import io.github.shengdoupi.springframework.beans.factory.BeanFactory;

/**
 * @author zhoukehh
 * @date 2024/4/26
 * @description Configurable bean factory, provides facilities to configure a bean factory.
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    
    /**
     * Add a new BeanPostProcessor that will get applied to beans created by this factory.
     * To be invoked during factory configuration.
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    
    /**
     * Destroy al singleton beans in this bean factory, include inner beans that registered as disposable bean.
     */
    void destroySingletons();
}
