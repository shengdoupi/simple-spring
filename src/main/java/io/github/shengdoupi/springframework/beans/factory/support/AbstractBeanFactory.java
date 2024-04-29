package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.config.BeanPostProcessor;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.support.DefaultSingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanFactory, ConfigurableBeanFactory {
    
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null,null);
    }
    
    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, null, args);
    }
    
    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return doGetBean(beanName, null);
    }
    
    protected <T> T doGetBean(String beanName, Class<T> requiredType, Object... args) {
        Object bean = null;
        bean = getSingleton(beanName);
        if (bean == null) {
            BeanDefinition beanDefinition = getBeanDefinition(beanName);
            bean = creatBean(beanName, beanDefinition, args);
        }
        return adaptBeanInstance(beanName, bean, requiredType);
    }
    
    /**
     * todo realize
     * @param beanName
     * @param beanInstance
     * @param requiredType
     * @return
     * @param <T>
     */
    <T> T adaptBeanInstance(String beanName, Object beanInstance, Class<T> requiredType) {
        return (T) beanInstance;
    }
    
    /**
     * Get bean definition.
     *
     * @param beanName
     * @return Bean definition.
     * @throws BeansException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    
    /**
     * Create bean.
     *
     * @param beanName
     * @param beanDefinition
     * @return Bean object.
     * @throws BeansException
     */
    protected abstract Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;
    
    
    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        this.beanPostProcessorList.remove(postProcessor);
        this.beanPostProcessorList.add(postProcessor);
    }
    
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessorList;
    }
}
