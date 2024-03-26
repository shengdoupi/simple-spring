package io.github.shengdoupi.springframework.beans.factory;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description Bean factory.
 */
public interface BeanFactory {
    
    /**
     * Get bean.
     * @param beanName
     * @return Bean
     * @throws BeansException
     */
    Object getBean(String beanName) throws BeansException;
}
