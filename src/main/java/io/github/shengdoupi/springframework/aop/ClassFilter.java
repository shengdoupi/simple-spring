package io.github.shengdoupi.springframework.aop;

/**
 * @author zhoukehh
 * @date 2024/5/13
 * @description Match a pointcut to a given set of classes.
 */
public interface ClassFilter {
    
    /**
     * Return whether the pointcut matches a given class of interface.
     * @param clazz
     * @return
     */
    boolean matches(Class<?> clazz);
}
