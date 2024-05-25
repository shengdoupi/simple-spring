package io.github.shengdoupi.springframework.aop.framework;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/23
 * @description Factory interface for advisor chains.
 */
public interface AdvisorChainFactory {
    
    /**
     *
     * @param advisedSupport
     * @param method
     * @param targetClass
     * @return
     */
    List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport advisedSupport, Method method, Class<?> targetClass);
}
