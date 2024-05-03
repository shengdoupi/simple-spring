package io.github.shengdoupi.springframework.beans.factory;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/5/2
 * @description Callback that allows a bean to be aware of the bean class loader
 * -- that the bean factory used to load bean classes.
 */
public interface BeanClassLoaderAware extends Aware {
    
    /**
     * Callback that supplies the bean class loader to a bean instance.
     * Invoked after properties population but before an init callback such as
     * afterPropertiesSet() or a custom init-method.
     * @param classLoader
     * @throws BeansException
     */
    void setBeanClassLoader(ClassLoader classLoader) throws BeansException;
}
