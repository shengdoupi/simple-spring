package io.github.shengdoupi.springframework.aop;

/**
 * @author zhoukehh
 * @date 2024/5/13
 * @description A pointcut is used for method matching.
 */
public interface Pointcut {
    
    /**
     * Get the classFilter of this pointcut.
     * @return
     */
    ClassFilter getClassFilter();
    
    /**
     * Get the method matcher fo this pointcut.
     * @return
     */
    MethodMatcher getMethodMatcher();
}
