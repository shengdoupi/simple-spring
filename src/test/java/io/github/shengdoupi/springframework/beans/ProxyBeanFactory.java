package io.github.shengdoupi.springframework.beans;

import io.github.shengdoupi.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoukehh
 * @date 2024/5/5
 * @description
 */
public class ProxyBeanFactory implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() throws BeansException {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            Map<String, String> map = new HashMap<>();
            map.put("01", "zhouke");
            return "你被代理了 " + method.getName() + " " + map.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, invocationHandler);
    }
    
    @Override
    public Class<?> getObjectType() throws BeansException {
        return IUserDao.class;
    }
}
