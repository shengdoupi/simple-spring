package io.github.shengdoupi.springframework.beans.factory.config;

import com.sun.istack.internal.Nullable;
import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description Factory hook that allows for custom modification of new bean instances.
 */
public interface BeanPostProcessor {
    
    /**
     * Modify a bean before its initialization.
     * @param bean
     * @param beanName
     * @return The bean instance to use, if null, no subsequent BeanPostProcessors will be invoked.
     * @throws BeansException
     */
    @Nullable
    default Object postProcessBeanBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    
    /**
     * Modify a bean after its initialization.
     * @param bean
     * @param beanName
     * @return The bean instance to use, if null, no subsequent BeanPostProcessors will be invoked.
     * @throws BeansException
     */
    @Nullable
    default Object postProcessBeanAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
