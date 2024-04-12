package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.PropertyValue;
import io.github.shengdoupi.springframework.beans.PropertyValues;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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
            applyPropertyValues(beanDefinition, beanName, bean);
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
    
    protected void applyPropertyValues(BeanDefinition beanDefinition, String beanName, Object bean) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (null == propertyValues) {
                return;
            }
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) propertyValue.getValue();
                    value = getBean(beanReference.getBeanName());
                }
                setFieldValue(beanName, bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Apply property values error", e);
        }
        
    }
    
    protected void setFieldValue(String beanName, Object bean, String propertyName, Object propertyValue) {
        try {
            Class clazz = getBeanDefinition(beanName).getBeanClass();
            Field field = clazz.getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(bean, propertyValue);
        } catch ( NoSuchFieldException | SecurityException e) {
            throw new BeansException("Reflection get field error " + beanName, e);
        } catch ( IllegalArgumentException | IllegalAccessException e) {
            throw new BeansException("Reflection set field value error", e);
        }
    }
}
