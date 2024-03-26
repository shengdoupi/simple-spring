package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
