package io.github.shengdoupi.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zhoukehh
 * @date 2024/5/13
 * @description Match a pointcut to a method.
 */
public interface MethodMatcher {
    
    /**
     * Return whether the given method matches.
     * @param method
     * @param targetClazz
     * @return
     */
    boolean matches(Method method, Class<?> targetClazz);
}
