package io.github.shengdoupi.springframework.aop.aspectj;

import io.github.shengdoupi.springframework.aop.Pointcut;
import io.github.shengdoupi.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description A class realized PointcutAdvisor.
 */
public class AspectJPointcutAdvisor implements PointcutAdvisor {
    private Pointcut pointcut;
    
    private Advice advice;
    
    public AspectJPointcutAdvisor(Pointcut pointcut, Advice advice) {
        this.pointcut = pointcut;
        this.advice = advice;
    }
    
    @Override
    public Advice getAdvice() {
        return advice;
    }
    
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }
}
