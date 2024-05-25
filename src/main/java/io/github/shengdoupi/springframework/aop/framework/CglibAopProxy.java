package io.github.shengdoupi.springframework.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/23
 * @description
 */
public class CglibAopProxy implements AopProxy {
    
    private final AdvisedSupport advised;
    
    public CglibAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }
    
    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass().getInterfaces());
        // Intercept logic.
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }
    
    
    /**
     * This MethodInterceptor is defined in Cglib.
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        
        private final AdvisedSupport advised;
        
        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }
        
        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            // Target object.
            Object target = advised.getTargetSource().getTarget();
            // Target class.
            Class<?> targetClass = advised.getTargetSource().getTargetClass() == null ? target.getClass() : advised.getTargetSource().getTargetClass();
            // Advisor chain.
            List<Object> advisorChain = advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
            // Method invocation.
            MethodInvocation methodInvocation = new CglibMethodInvocation(method, target, proxy, args, targetClass, advisorChain);
            if (null == advisorChain || advisorChain.isEmpty()) {
                return methodProxy.invoke(target, args);
            } else {
                // The advisors will do the interceptor logic.
                return methodInvocation.proceed();
            }
        }
    }
    
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        
        public CglibMethodInvocation(Method method, Object target, Object proxy, Object[] arguments,
                                     Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {
            super(method, target, proxy, arguments, targetClass, interceptorsAndDynamicMethodMatchers);
        }
        
        @Override
        public Object proceed() throws Throwable {
            return super.proceed();
        }
    }
}
