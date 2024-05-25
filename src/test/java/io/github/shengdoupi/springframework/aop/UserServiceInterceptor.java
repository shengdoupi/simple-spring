package io.github.shengdoupi.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description
 */
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            System.out.println("User service is intercepted.");
            invocation.proceed();
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
