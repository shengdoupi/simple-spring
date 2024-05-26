package io.github.shengdoupi.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author zhoukehh
 * @date 2024/5/26
 * @description
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] args, Object target) {
        try {
            System.out.println("User service is intercepted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
