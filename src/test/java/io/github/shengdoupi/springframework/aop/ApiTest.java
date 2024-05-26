package io.github.shengdoupi.springframework.aop;

import io.github.shengdoupi.springframework.aop.aspectj.AspectJExpressionPointcut;
import io.github.shengdoupi.springframework.aop.aspectj.AspectJPointcutAdvisor;
import io.github.shengdoupi.springframework.aop.framework.AdvisedSupport;
import io.github.shengdoupi.springframework.aop.framework.JdkDynamicAopProxy;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.support.DefaultListableBeanFactory;
import io.github.shengdoupi.springframework.context.ApplicationContext;
import io.github.shengdoupi.springframework.context.support.AbstractXmlApplicationContext;
import io.github.shengdoupi.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description
 */
public class ApiTest {
    
    private AdvisedSupport advisedSupport;
    
    @Before
    public void setup(){
        advisedSupport = new AdvisedSupport();
        // 定义切面
        String expression = "execution(* io.github.shengdoupi.springframework.aop.UserService.*(..))";
        // 定义拦截逻辑
        UserServiceInterceptor interceptor = new UserServiceInterceptor();
        PointcutAdvisor pointcutAdvisor = new AspectJPointcutAdvisor(expression, interceptor);
        List<Object> advisors = new ArrayList<>();
        advisors.add(pointcutAdvisor);
        advisedSupport.setAdvisors(advisors);
        // 目标对象
        UserService userService = new IUserService();
        advisedSupport.setTargetSource(new TargetSource(userService));
    }
    
    @Test
    public void aop_test() {
        // 代理对象
        UserService proxy = (UserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 执行代理对象的方法
        proxy.queryUserInfo();
    }
    
    @Test
    public void aop_with_bean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-aop.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo();
    }
}
