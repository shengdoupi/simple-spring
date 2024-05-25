package io.github.shengdoupi.springframework.aop.framework;

/**
 * @author zhoukehh
 * @date 2024/5/19
 * @description Delegate interface for spring AOP proxy, allowing for the creation of actual proxy objects.
 */
public interface AopProxy {
    
    /**
     * Create a new proxy object.
     * @return
     */
    Object getProxy();
}
