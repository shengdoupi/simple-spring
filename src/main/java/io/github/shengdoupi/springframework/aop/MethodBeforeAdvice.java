package io.github.shengdoupi.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description Advice call before a method is invoked.
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
    
    /**
     * Callback before a method is invoked.
     * @param method
     * @param args
     * @param target
     */
    void before(Method method, Object[] args, Object target);
}
