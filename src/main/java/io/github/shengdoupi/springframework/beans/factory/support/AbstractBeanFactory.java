package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.FactoryBean;
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
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport
        implements BeanFactory, ConfigurableBeanFactory {
    
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();
    
    private final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null, null);
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
        return getObjectForBeanInstance(beanName, bean, requiredType);
    }
    
    protected <T> T getObjectForBeanInstance(String beanName, Object bean, Class<T> requiredType) {
        if (!(bean instanceof FactoryBean)) {
            return adaptBeanInstance(beanName, bean, requiredType);
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if (null == object) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) bean;
            object = getObjectFromFactoryBean(beanName, factoryBean);
        }
        return (T) object;
    }
    
    /**
     * todo realize
     *
     * @param beanName
     * @param beanInstance
     * @param requiredType
     * @param <T>
     * @return
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
    
    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessorList;
    }
}
