package io.github.shengdoupi.springframework.beans.factory.support;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.DisposableBean;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author zhoukehh
 * @date 2024/4/30
 * @description Adapter that implements DisposableBean and Runnable interface
 * to perform various steps on destruction of a bean.
 */
public class DisposableBeanAdapter implements DisposableBean {
    
    private final Object bean;
    
    private final String beanName;
    
    private final String destroyMethodName;
    
    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }
    
    @Override
    public void destroy() throws Exception {
        try {
            if (bean instanceof DisposableBean) {
                ((DisposableBean) bean).destroy();
                return;
            }
            if (StringUtils.isNotBlank(destroyMethodName)
                    && !(bean instanceof DisposableBean && "destroy".equals(destroyMethodName))) {
                Method destroyMethod = this.bean.getClass().getMethod(destroyMethodName);
                if (Objects.isNull(destroyMethod)) {
                    throw new Exception("Destroy method null error.");
                }
                destroyMethod.invoke(bean);
            }
            
        } catch (Exception e) {
            throw new BeansException("Destroy bean error.", e);
        }
    }
}
