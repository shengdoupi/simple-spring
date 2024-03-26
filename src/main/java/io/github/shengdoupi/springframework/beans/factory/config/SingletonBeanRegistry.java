package io.github.shengdoupi.springframework.beans.factory.config;

/**
 * @author zhoukehh
 * @date 2024/3/26
 * @description
 */
public interface SingletonBeanRegistry {
    /**
     * Get singleton.
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
