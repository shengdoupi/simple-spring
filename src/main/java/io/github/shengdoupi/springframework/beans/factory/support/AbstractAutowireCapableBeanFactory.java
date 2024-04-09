package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Abstract autowire capable bean factory.
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
    
    @Override
    public Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiate bean failed.", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
    
    /**
     * Instantialize bean.
     *
     * @param beanName
     * @param beanDefinition
     * @param args
     * @return
     * @throws BeansException
     */
    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        // Get constructor.
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] ctors = beanClass.getDeclaredConstructors();
        Object[] argsToUse = null;
        Constructor ctorToUse = null;
        if (null != args) {
            argsToUse = args;
        } else {
            argsToUse = new Object[0];
        }
        for (Constructor<?> ctor : ctors) {
            if (ctor.getParameterCount() == argsToUse.length) {
                ctorToUse = ctor;
                break;
            }
        }
        // Instantiation.
        return instantiationStrategy.instantiate(beanDefinition, beanName, ctorToUse, argsToUse);
    }
}
