package io.github.shengdoupi.springframework.beans.factory.config;

import io.github.shengdoupi.springframework.beans.PropertyValues;

/**
 * @author zhoukehh
 * @date 2024/3/21
 * @description Bean definition.
 */
public class BeanDefinition {
    /**
     * Scope identifier for the standard singleton scope
     */
    String SCOPE_SINGLETON = "singleton";
    
    /**
     * Scope identifier for the standard prototype scope
     */
    String SCOPE_PROTOTYPE = "prototype";
    
    private Class beanClass;
    
    private PropertyValues propertyValues;
    
    private String scope = SCOPE_SINGLETON;
    
    private boolean singleton = true;
    
    private boolean prototype = false;
    
    private boolean lazyInit = false;
    
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
    
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
    
    public boolean isSingleton() {
        return this.singleton;
    }
    
    public boolean isPrototype() {
        return this.prototype;
    }
    
    public boolean isLazyInit() {
        return this.lazyInit;
    }
    
    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }
}
