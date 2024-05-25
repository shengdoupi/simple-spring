package io.github.shengdoupi.springframework.aop.framework;


import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/19
 * @description JDK-based AopProxy implementation for the spring framework.
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    
    private final AdvisedSupport advised;
    
    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
    
    /**
     * Create a proxy.
     *
     * @return
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass().getInterfaces(), this);
    }
    
    /**
     * Processes a method invocation on a proxy instance and returns the result.
     * This method will be invoked on an invocation handler when a method is invoked
     * on a proxy instance that it associated with.
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Get the advisor chain.
        List<Object> interceptors = advised.getInterceptorsAndDynamicInterceptionAdvice(method, null);
        if (interceptors != null && interceptors.size() > 0) {
            // Encapsulate the interceptors to a ReflectiveMethodInvocation object.
            MethodInvocation methodInvocation = new ReflectiveMethodInvocation(method, advised.getTargetSource().getTarget(), proxy, args,
                    advised.getTargetSource().getTargetClass(), interceptors);
            // Proceed to the joinpoint through the interceptor chain.
            return methodInvocation.proceed();
        } else {
            return method.invoke(advised.getTargetSource().getTarget(), args);
        }
    }
}
