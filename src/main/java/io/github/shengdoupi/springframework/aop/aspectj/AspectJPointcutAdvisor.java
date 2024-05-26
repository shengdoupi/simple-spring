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
    
    private String expression;
    
    private Pointcut pointcut;
    
    private Advice advice;
    
    public AspectJPointcutAdvisor() {}
    
    public AspectJPointcutAdvisor(String expression, Advice advice) {
        this.expression = expression;
        this.advice = advice;
    }
    
    @Override
    public Advice getAdvice() {
        return advice;
    }
    
    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }
    
    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
