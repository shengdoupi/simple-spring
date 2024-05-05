package io.github.shengdoupi.springframework.beans.factory;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/5/5
 * @description Interface to be implemented by objects used within a BeanFactory which
 * are themselves factories for individual objects.
 * If a bean implements this interface, it is used as a factory for an object to expose,
 * not directly as an instance that will be exposed itself;
 */
public interface FactoryBean<T> {
    
    /**
     * Return an instance of the object managed by this factory;
     * @return
     * @throws BeansException
     */
    T getObject() throws BeansException;
    
    /**
     * Return the type of object that this FactoryBean creates;
     * @return
     * @throws BeansException
     */
    Class<?> getObjectType() throws BeansException;
    
    /**
     * Return if the object managed by this FactoryBean is singleton.
     * @return
     */
    default boolean isSingleton() {
        return true;
    }
}
