package io.github.shengdoupi.springframework.aop.framework;

import io.github.shengdoupi.springframework.aop.Advisor;
import io.github.shengdoupi.springframework.aop.ClassFilter;
import io.github.shengdoupi.springframework.aop.MethodMatcher;
import io.github.shengdoupi.springframework.aop.PointcutAdvisor;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/23
 * @description
 */
public class DefaultAdvisorChainFactory implements AdvisorChainFactory{
    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport advisedSupport, Method method, Class<?> targetClass) {
        Advisor[] advisors = advisedSupport.getAdvisors().toArray(new Advisor[0]);
        List<Object> interceptors = new ArrayList<>(advisors.length);
        Class<?> actualClass = targetClass == null ? method.getDeclaringClass() : targetClass;
        for (Advisor advisor : advisors) {
            if (advisor instanceof PointcutAdvisor) {
                ClassFilter classFilter = ((PointcutAdvisor) advisor).getPointcut().getClassFilter();
                if (!classFilter.matches(actualClass)) {
                    continue;
                }
                MethodMatcher methodMatcher = ((PointcutAdvisor) advisor).getPointcut().getMethodMatcher();
                if (!methodMatcher.matches(method, actualClass)) {
                    continue;
                }
                interceptors.add((MethodInterceptor) advisor.getAdvice());
            }
        }
        return interceptors;
    }
}
