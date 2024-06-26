package io.github.shengdoupi.springframework.beans.factory.config;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Interface that defines a registry for shared instances.
 */
public interface SingletonBeanRegistry {
    /**
     * Get singleton.
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
    
    /**
     * Register the given existing object as singleton in the bean registry.
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);
}
