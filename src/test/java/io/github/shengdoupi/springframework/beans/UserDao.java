package io.github.shengdoupi.springframework.beans;

import java.util.HashMap;

/**
 * @author zhoukehh
 * @date 2024/4/12
 * @description User dao.
 */
public class UserDao {
    private static final HashMap<String, String> userInfoCache = new HashMap<>();
    
    static {
        userInfoCache.put("01", "zhouke");
    }
    
    public String getUserName(String userId) {
        return userInfoCache.get(userId);
    }
    
    public void destroyMethod() {
        System.out.println("UserDao destroy method is invoked");
    }
    
    public void initializeMethod() {
        System.out.println("UserDao initialize method is invoked");
    }
}
