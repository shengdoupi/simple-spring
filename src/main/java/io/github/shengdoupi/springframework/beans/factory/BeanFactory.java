package io.github.shengdoupi.springframework.beans.factory;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Bean factory.
 */
public interface BeanFactory {
    
    /**
     * Get bean.
     * @param beanName
     * @return Bean
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;
    
    /**
     * Get bean with args.
     * @param beanName
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String beanName, Object... args) throws BeansException;
    
    /**
     * Get bean by required type.
     * @param beanName
     * @param requiredType Type the bean must match, can be an interface or superclass.
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;
    
    /**
     * Get bean by required type.
     * @param requiredType Type the bean must match, can be an interface or superclass.
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> T getBean(Class<T> requiredType) throws BeansException;
}
