package io.github.shengdoupi.springframework.beans.factory.config;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description Factory hook that allows for custom modification of an application context's bean definitions.
 *
 */
public interface BeanFactoryPostProcessor {
    
    /**
     * Modify the application context's bean factory after its standard initialization.
     * All bean definitions has been loaded, but no bean has been instantiated yet.
     * This allows overriding or adding properties for beans' definition.
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
