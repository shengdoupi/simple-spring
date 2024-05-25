package io.github.shengdoupi.springframework.aop.framework;

import io.github.shengdoupi.springframework.aop.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/23
 * @description Spring's implementation of AOP Alliance MethodInvocation interface;
 * Invokes the target object using reflection.
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    
    protected final Method method;
    
    protected final Object target;
    
    protected final Object proxy;
    
    protected final Object[] arguments;
    
    protected final Class<?> targetClass;
    
    protected final List<Object> interceptorsAndDynamicMethodMatchers;
    
    private int currentChainIndex = -1;
    
    public ReflectiveMethodInvocation(Method method, Object target, Object proxy, Object[] arguments, Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {
        this.method = method;
        this.target = target;
        this.proxy = proxy;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }
    
    @Override
    public Method getMethod() {
        return method;
    }
    
    @Override
    public Object[] getArguments() {
        return arguments;
    }
    
    /**
     * Method invoke.
     * @return
     * @throws Throwable
     */
    @Override
    public Object proceed() throws Throwable {
        // When the invocation time of proceed method equals to the interceptors' count, the chain is over, using reflect to invoke the target method.
        if (currentChainIndex == interceptorsAndDynamicMethodMatchers.size() - 1) {
            return method.invoke(target, arguments);
        }
        // Get current advisor(interceptor).
        Object interceptorAndDynamicMethodMatcher = interceptorsAndDynamicMethodMatchers.get(++currentChainIndex);
        
        // Dynamic method match.
        if (interceptorAndDynamicMethodMatcher instanceof MethodMatcher) {
            if (((MethodMatcher) interceptorAndDynamicMethodMatcher).matches(method, targetClass)) {
                return ((MethodInterceptor) interceptorAndDynamicMethodMatcher).invoke(this);
            } else {
                // Dynamic matching failed. Skip this interceptor and invoke the next in this chain.
                return proceed();
            }
        } else {
            // Means this object is an interceptor, just invoke it.
            // And the invocation's proceed method will be invoked again in the interceptor's invoke method.
            return ((MethodInterceptor) interceptorAndDynamicMethodMatcher).invoke(this);
        }
    }
    
    @Override
    public Object getThis() {
        return target;
    }
    
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
