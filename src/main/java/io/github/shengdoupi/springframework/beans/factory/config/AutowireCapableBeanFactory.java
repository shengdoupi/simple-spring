package io.github.shengdoupi.springframework.beans.factory.config;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;

/**
 * @author zhoukehh
 * @date 2024/4/26
 * @description Autowire capable bean factory, interface to be implemented by factory that are capable of autowiring.
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    
    /**
     * Apply to the given existing bean instance, invoking their postProcessBeforeInitialization methods.
     * @param bean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName);
    
    /**
     * Apply to the given existing bean instance, invoking their postProcessAfterInitialization methods.
     * @param bean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName);
}
