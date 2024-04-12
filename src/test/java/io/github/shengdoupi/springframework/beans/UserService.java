package io.github.shengdoupi.springframework.beans;

/**
 * @author zhoukehh
 * @date 2024/4/12
 * @description User service.
 */
public class UserService {
    private UserDao userDao;
    
    public void queryUserInfo(String userId) {
        printUserInfo(userDao.getUserName(userId));
    }
    
    private void printUserInfo(String userName) {
        System.out.println("Query user info: " + userName);
    }
    
}
