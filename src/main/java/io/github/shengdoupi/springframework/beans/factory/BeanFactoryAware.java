package io.github.shengdoupi.springframework.beans.factory;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/5/2
 * @description Interface to be implemented by beans that wish to be aware of their owner -- BeanFactory.
 */
public interface BeanFactoryAware extends Aware{
    
    /**
     * Callback that supplies the owning bean factory to a bean instance.
     * Invoked after properties population but before an init callback such as
     * afterPropertiesSet() or a custom init-method.
     * @param beanFactory
     * @throws BeansException
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
