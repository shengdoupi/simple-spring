package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoukehh
 * @date 2024/5/5
 * @description Support base class for singleton registries which need to handle FactoryBean instances.
 */
public class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{
    
    /**
     * Cache of singleton object created by factory beans: FactoryBean name to object.
     */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>(16);
    
    /**
     * Determine the type for the given FactoryBean.
     * @param factoryBean
     * @return
     */
    protected Class<?> getClassForFactoryBean(FactoryBean factoryBean) {
        try {
            return factoryBean.getObjectType();
        } catch (Exception e) {
            throw new BeansException("Get clas for factory bean error", e);
        }
    }
    
    /**
     * Obtain an object to expose from the given FactoryBean, if cached in factoryBeanObjectCache.
     * @param factoryBeanName
     * @return
     */
    protected Object getCachedObjectForFactoryBean(String factoryBeanName) {
        return this.factoryBeanObjectCache.get(factoryBeanName);
    }
    
    /**
     * Obtain an object to expose from the given FactoryBean.
     * @param beanName
     * @param factory
     * @return
     */
    protected Object getObjectFromFactoryBean(String beanName, FactoryBean<?> factory) {
        Object object;
        if (factory.isSingleton()) {
            object = this.factoryBeanObjectCache.get(beanName);
            if (null == object) {
                object = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, object);
            }
        } else {
            object = doGetObjectFromFactoryBean(factory, beanName);
        }
        return object;
    }
    
    /**
     * Obtain an object to expose from the given FactoryBean.
     * @param factory
     * @param beanName
     * @return
     */
    private Object doGetObjectFromFactoryBean(FactoryBean<?> factory, String beanName) {
        Object object;
        try {
            object = factory.getObject();
        } catch (Exception e) {
            throw new BeansException("Get object from factory bean error, beanName: " + beanName, e);
        }
        return object;
    }
}
