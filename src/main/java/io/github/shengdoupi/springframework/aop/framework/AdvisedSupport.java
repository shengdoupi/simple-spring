package io.github.shengdoupi.springframework.aop.framework;

import io.github.shengdoupi.springframework.aop.MethodMatcher;
import io.github.shengdoupi.springframework.aop.TargetSource;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/18
 * @description Class for AOP proxy configuration manager.
 */
public class AdvisedSupport {
    
    /**
     * Method matcher.
     */
    private MethodMatcher methodMatcher;
    
    /**
     * Target which is in proxy.
     */
    private TargetSource targetSource;
    
    /**
     * Advisor chain.
     */
    private List<Object> advisors;
    
    /**
     * Advisor chain factory.
     */
    private AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();
    
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }
    
    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
    
    public TargetSource getTargetSource() {
        return targetSource;
    }
    
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }
    
    public List<Object> getAdvisors() {
        return advisors;
    }
    
    public void setAdvisors(List<Object> advisors) {
        this.advisors = advisors;
    }
    
    public AdvisorChainFactory getAdvisorChainFactory() {
        return advisorChainFactory;
    }
    
    public void setAdvisorChainFactory(AdvisorChainFactory advisorChainFactory) {
        this.advisorChainFactory = advisorChainFactory;
    }
    
    /**
     * Return method interceptor chain.
     * @param method
     * @param targetClass
     * @return
     */
    List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) {
        return this.getAdvisorChainFactory().getInterceptorsAndDynamicInterceptionAdvice(this, method, targetClass);
    }
}

