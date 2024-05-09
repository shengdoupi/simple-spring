package io.github.shengdoupi.springframework.context.event;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.BeanFactoryAware;
import io.github.shengdoupi.springframework.context.ApplicationEvent;
import io.github.shengdoupi.springframework.context.ApplicationListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();
    
    private BeanFactory beanFactory;
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    
    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) applicationListener);
    }
    
    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.remove(applicationListener);
    }
    
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        List<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> applicationListener : applicationListeners) {
            if (supportEvent(applicationListener, event)) {
                allListeners.add(applicationListener);
            }
        }
        return allListeners;
    }
    
    /**
     * 判断监听者是否对事件感兴趣, 注意这里的实现只针对 JDK 实例化策略生成的实例，不支持被 CGLIB 代理生成的实例
     * @param listener
     * @param event
     * @return
     */
    protected boolean supportEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        Type type = listener.getClass().getGenericInterfaces()[0];
        Type actualTypeAugment = ((ParameterizedType) type).getActualTypeArguments()[0];
        String className = actualTypeAugment.getTypeName();
        Class<?> eventClassname;
        try {
            eventClassname = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("Wrong event class name: " + className);
        }
        // A.isAssignableFrom(B) -> A 是 B 的父类
        return eventClassname.isAssignableFrom(event.getClass());
    }
}
