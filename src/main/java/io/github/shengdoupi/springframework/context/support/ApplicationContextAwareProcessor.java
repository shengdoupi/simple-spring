package io.github.shengdoupi.springframework.context.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.BeanPostProcessor;
import io.github.shengdoupi.springframework.context.ApplicationContext;
import io.github.shengdoupi.springframework.context.ApplicationContextAware;

/**
 * @author zhoukehh
 * @date 2024/5/2
 * @description BeanPostProcessor implementation that supplies the ApplicationContext
 * to beans that implement the ApplicationContextAware interface.
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    
    private final ApplicationContext applicationContext;
    
    /**
     * Create a ApplicationContextAwareProcessor for a given context.
     * @param applicationContext
     */
    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public Object postProcessBeanBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }
    
    /**
     * Modify a bean after its initialization.
     * @param bean
     * @param beanName
     * @return The bean instance to use, if null, no subsequent BeanPostProcessors will be invoked.
     * @throws BeansException
     */
    @Override
    public Object postProcessBeanAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
