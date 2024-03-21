package io.github.shengdoupi.ioc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoukehh
 * @date 2024/3/21
 * @description
 */
public class BeanFactory {
    
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    
    private Map<String, Object> singletonObjects = new HashMap<>();
    
    /**
     * Register bean definition.
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
    
    /**
     * Get singleton bean.
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (singletonObject != null) {
            return singletonObject;
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        singletonObject = createBean(beanDefinition);
        singletonObjects.put(beanName, singletonObject);
        return singletonObject;
    }
    
    /**
     * Create singleton bean.
     * @param beanDefinition
     * @return
     */
    private Object createBean(BeanDefinition beanDefinition) {
        Object beanObject = null;
        try {
            beanObject = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return beanObject;
        }
        return beanObject;
    }
}
