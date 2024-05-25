package io.github.shengdoupi.springframework.aop;

/**
 * @author zhoukehh
 * @date 2024/5/23
 * @description Superinterface for all advisors that driven by a pointcut.
 */
public interface PointcutAdvisor extends Advisor {
    
    /**
     * Get the pointcut that drives this advisor.
     * @return
     */
    Pointcut getPointcut();
}
