package io.github.shengdoupi.springframework.beans.common;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.UserService;
import io.github.shengdoupi.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeanBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setCity("Shanghai");
        }
        return bean;
    }

}
