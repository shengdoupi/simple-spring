package io.github.shengdoupi.ioc;

/**
 * @author zhoukehh
 * @date 2024/3/21
 * @description Bean definition.
 */
public class BeanDefinition {
    private Class beanClass;
    
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
    
    public Class getBeanClass() {
        return this.beanClass;
    }
    
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
