package io.github.shengdoupi.springframework.beans;

import io.github.shengdoupi.springframework.beans.factory.DisposableBean;
import io.github.shengdoupi.springframework.beans.factory.InitializingBean;

/**
 * @author zhoukehh
 * @date 2024/4/12
 * @description User service.
 */
public class UserService implements DisposableBean, InitializingBean {
    private UserDao userDao;
    
    private String country;
    
    private String city;
    
    public void queryUserInfo(String userId) {
        printUserInfo(userDao.getUserName(userId));
    }
    
    private void printUserInfo(String userName) {
        System.out.println("Query user info: " + userName + getCountry() + getCity());
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
}
