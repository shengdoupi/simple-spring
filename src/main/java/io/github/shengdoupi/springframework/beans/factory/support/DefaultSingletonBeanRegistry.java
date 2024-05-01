package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.DisposableBean;
import io.github.shengdoupi.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Generic registry for shared bean instances.
 * Also supports registration for DisposableBean instances, dependencies of beans can be registered
 * to enforce an appropriate shutdown order.
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    
    Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();
    
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
    
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }
    
    public void destroySingletons() {
        try {
            Set<String> beanNames = new HashSet<>(disposableBeans.keySet());
            for (String beanName : beanNames) {
                DisposableBean bean = disposableBeans.remove(beanName);
                bean.destroy();
            }
        } catch (Exception e) {
            throw new BeansException("Destroy singletons error", e);
        }
    }
}
