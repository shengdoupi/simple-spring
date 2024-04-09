package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.support.DefaultSingletonBeanRegistry;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) throws BeansException{
        return doGetBean(beanName, null);
    }
    
    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }
    
    protected Object doGetBean(String beanName, Object... args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return creatBean(beanName, beanDefinition, args);
    }
    
    /**
     * Get bean definition.
     * @param beanName
     * @return Bean definition.
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    
    /**
     * Create bean.
     * @param beanName
     * @param beanDefinition
     * @return Bean object.
     * @throws BeansException
     */
    protected abstract Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
    
}
