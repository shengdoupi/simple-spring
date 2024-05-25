package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.PropertyValue;
import io.github.shengdoupi.springframework.beans.PropertyValues;
import io.github.shengdoupi.springframework.beans.factory.Aware;
import io.github.shengdoupi.springframework.beans.factory.BeanClassLoaderAware;
import io.github.shengdoupi.springframework.beans.factory.BeanFactoryAware;
import io.github.shengdoupi.springframework.beans.factory.BeanNameAware;
import io.github.shengdoupi.springframework.beans.factory.DisposableBean;
import io.github.shengdoupi.springframework.beans.factory.InitializingBean;
import io.github.shengdoupi.springframework.beans.factory.config.AutowireCapableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.config.BeanPostProcessor;
import io.github.shengdoupi.springframework.beans.factory.config.BeanReference;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Abstract autowire capable bean factory.
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    
    @Override
    public Object creatBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException {
        Object bean = null;
        try {
            // 创建Bean实例
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 属性注入
            applyPropertyValues(beanDefinition, beanName, bean);
            // 初始化Bean
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiate bean failed.", e);
        }
        //
        registerDisposableBeanIfNecessary(bean, beanDefinition, beanName);
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
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
        } catch (NoSuchFieldException | SecurityException e) {
            throw new BeansException("Reflection get field error " + beanName, e);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new BeansException("Reflection set field value error", e);
        }
    }
    
    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        
        if (bean instanceof Aware) {
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(super.getClassLoader());
            }
        }
        
        // 执行初始化前的自定义方法
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 执行初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        // 执行初始化后的自定义方法
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }
    
    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        try {
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
                return;
            }
            if (StringUtils.isBlank(beanDefinition.getInitMethodName())) {
                return;
            }
            String initMethodName = beanDefinition.getInitMethodName();
            Class clazz = beanDefinition.getBeanClass();
            Method initMethod = clazz.getDeclaredMethod(initMethodName);
            if (Objects.isNull(initMethod)) {
                throw new Exception("Init method:" + initMethodName + "can not find error.");
            }
            initMethod.invoke(bean);
        } catch (Exception e) {
            throw new BeansException("Init methods invoke error.", e);
        }
        
    }
    
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor postProcessor : getBeanPostProcessors()) {
            Object current = postProcessor.postProcessBeanBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
    
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor postProcessor : getBeanPostProcessors()) {
            Object current = postProcessor.postProcessBeanAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
    
    private void registerDisposableBeanIfNecessary(Object bean, BeanDefinition beanDefinition, String beanName) {
        if (null == bean) {
            return;
        }
        if (!beanDefinition.isSingleton()) {
            return;
        }
        if (bean instanceof DisposableBean || StringUtils.isNotBlank(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }
}
