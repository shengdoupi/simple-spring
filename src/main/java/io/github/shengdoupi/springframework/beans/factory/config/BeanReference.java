package io.github.shengdoupi.springframework.beans.factory.config;

/**
 * @author zhoukehh
 * @date 2024/4/11
 * @description
 */
public interface BeanReference {
    /**
     * Return the target bean name this reference points to.
     * @return
     */
    String getBeanName();
}
