package io.github.shengdoupi.springframework.beans.factory.config;

import io.github.shengdoupi.springframework.beans.PropertyValues;

/**
 * @author zhoukehh
 * @date 2024/3/21
 * @description Bean definition.
 */
public class BeanDefinition {
    private Class beanClass;
    
    private PropertyValues propertyValues;
    
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
    
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }
    
    public Class getBeanClass() {
        return this.beanClass;
    }
    
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
    
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
    
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
