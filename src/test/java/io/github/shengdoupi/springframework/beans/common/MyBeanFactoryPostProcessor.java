package io.github.shengdoupi.springframework.beans.common;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.PropertyValue;
import io.github.shengdoupi.springframework.beans.PropertyValues;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.config.BeanFactoryPostProcessor;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("country", "China"));
    }
}
