package io.github.shengdoupi.springframework.aop.framework.adapter;

import io.github.shengdoupi.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description Adapter to enable MethodBeforeAdvice to be used in Spring AOP framework.
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    
    
    private MethodBeforeAdvice methodBeforeAdvice;
    
    public MethodBeforeAdviceInterceptor(){}
    
    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice methodBeforeAdvice) {
        this.methodBeforeAdvice = methodBeforeAdvice;
    }
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        methodBeforeAdvice.before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.proceed();
    }
}
