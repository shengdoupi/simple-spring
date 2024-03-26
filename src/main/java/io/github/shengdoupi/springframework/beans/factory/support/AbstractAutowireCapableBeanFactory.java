package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Abstract autowire capable bean factory.
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    
    @Override
    public Object creatBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new BeansException("Create bean from bean definition exception.", exception);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
