package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author zhoukehh
 * @date 2024/4/9
 * @description Cglib subclassing instantiation strategy.
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> ctor, Object... args) {
        if (null == ctor) {
            throw new BeansException("Constructor must not be null");
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (0 == ctor.getParameterCount()) {
            return enhancer.create();
        }
        return enhancer.create(ctor.getParameterTypes(), args);
    }
}
