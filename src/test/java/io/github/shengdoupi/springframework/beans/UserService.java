package io.github.shengdoupi.springframework.beans;

import io.github.shengdoupi.springframework.beans.factory.BeanClassLoaderAware;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.BeanFactoryAware;
import io.github.shengdoupi.springframework.beans.factory.BeanNameAware;
import io.github.shengdoupi.springframework.beans.factory.DisposableBean;
import io.github.shengdoupi.springframework.beans.factory.InitializingBean;
import io.github.shengdoupi.springframework.context.ApplicationContext;
import io.github.shengdoupi.springframework.context.ApplicationContextAware;
import io.github.shengdoupi.springframework.context.support.ApplicationContextAwareProcessor;

/**
 * @author zhoukehh
 * @date 2024/4/12
 * @description User service.
 */
public class UserService implements DisposableBean, InitializingBean, BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {
    private UserDao userDao;
    
    private String country;
    
    private String city;
    
    private String beanName;
    
    private BeanFactory beanFactory;
    
    private ClassLoader classLoader;
    
    private ApplicationContext applicationContext;
    
    private IUserDao iUserDao;
    
    public void queryUserInfo(String userId) {
        printUserInfo(iUserDao.queryUserInfo(userId));
    }
    
    private void printUserInfo(String userName) {
        System.out.println("Query user info: " + userName + " " + getCountry() + " " + getCity());
    }
    
    public UserDao getUserDao() {
        return userDao;
    }
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Override
    public void destroy() throws Exception {
        System.out.println("UserService destroy method is invoked.");
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserService initialize method is invoked.");
    }
    
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) throws BeansException {
        this.classLoader = classLoader;
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    
    @Override
    public void setBeanName(String beanName) throws BeansException {
        this.beanName = beanName;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public String getBeanName() {
        return beanName;
    }
    
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
    
    public ClassLoader getClassLoader() {
        return classLoader;
    }
    
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
