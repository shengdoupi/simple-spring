package io.github.shengdoupi.springframework.context.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.BeanFactoryPostProcessor;
import io.github.shengdoupi.springframework.beans.factory.config.BeanPostProcessor;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import io.github.shengdoupi.springframework.context.ConfigurableApplicationContext;
import io.github.shengdoupi.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    
    @Override
    public void refresh() throws BeansException {
        try {
            // 1. 创建 BeanFactory 并加载BeanDefinition
            refreshBeanFactory();
            ConfigurableListableBeanFactory beanFactory = getBeanFactory();
            // 2. Bean 实例化之前, 调用BeanFactoryPostProcessors
            invokeBeanFactoryPostProcessors(beanFactory);
            // 3. 注册 BeanPostProcessor
            registerBeanPostProcessors(beanFactory);
            // 4. 完成实例化单例Bean
            finishBeanFactoryInitialization(beanFactory);
        } catch (Exception e) {
            throw new BeansException("Application context refresh error", e);
        }
    }
    
    /**
     * Create bean factory, and load bean definitions.
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;
    
    public abstract ConfigurableListableBeanFactory getBeanFactory();
    
    /**
     * Instantiate and invoke all registered BeanFactoryPostProcessor beans
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beans = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor postProcessor : beans.values()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }
    }
    
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beans = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beans.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }
    
    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
        // 完成实例化单例Bean
        beanFactory.preInstantiateSingletons();
    }
    
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
    
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }
    
    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }
    
    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }
    
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name);
    }
    
    
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
    
    @Override
    public void registerShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                close();
            }
        }));
    }
    
    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
