package io.github.shengdoupi.springframework.beans.factory.config;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description Sub-interface of BeanPostProcessor that add callbacks before and after instantiation.
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{
    
    /**
     * Apply before the target bean gets instantiated.
     * @param beanClass
     * @param beanName
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);
    
    /**
     * Apply after the target bean gets instantiated.
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInstantiation(Object bean, String beanName);
}
