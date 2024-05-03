package io.github.shengdoupi.springframework.beans.factory;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/5/2
 * @description Interfaces to be implemented by beans that wish to be aware of their
 * bean name in this bean factory.
 */
public interface BeanNameAware extends Aware{
    
    /**
     * Set the name of the bean in the bean factory that created this bean.
     * Invoked after properties population but before an init callback such as
     * afterPropertiesSet() or a custom init-method.
     * @param beanName
     * @throws BeansException
     */
    void setBeanName(String beanName) throws BeansException;
}
