package io.github.shengdoupi.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author zhoukehh
 * @date 2024/5/23
 * @description Base interfaces holding AOP advice (action to take at a joinpoint.)
 */
public interface Advisor {
    
    /**
     * Return the advice part of this aspect.
     * @return
     */
    Advice getAdvice();
}
