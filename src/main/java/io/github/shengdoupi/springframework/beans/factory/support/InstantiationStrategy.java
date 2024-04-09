package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhoukehh
 * @date 2024/4/9
 * @description Instantiation strategy.
 */
public interface InstantiationStrategy {
    /**
     * Instantiate.
     * @param beanDefinition
     * @param beanName
     * @param ctor
     * @param args
     * @return bean
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object... args);
}
