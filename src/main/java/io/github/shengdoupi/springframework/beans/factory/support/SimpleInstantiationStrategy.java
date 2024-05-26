package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import javafx.util.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author zhoukehh
 * @date 2024/4/9
 * @description Simple instantiation strategy.
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object... args) {
        try {
            if (null == ctor) {
                ctor = beanDefinition.getBeanClass().getDeclaredConstructor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instantiateClass(ctor, args);
    }
    
    protected Object instantiateClass(Constructor<?> ctor, Object... args) {
        try {
            if (null == ctor) {
                throw new BeansException("Constructor must not be null");
            }
            ctor.setAccessible(true);
            int parameterCount = ctor.getParameterCount();
            if (parameterCount == 0) {
                return ctor.newInstance();
            }
            return ctor.newInstance(args);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e){
            throw new BeansException("Instantiate exception.", e);
        }
    }
}
