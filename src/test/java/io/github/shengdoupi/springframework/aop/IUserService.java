package io.github.shengdoupi.springframework.aop;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description
 */
public class IUserService implements UserService{
    @Override
    public void queryUserInfo() {
        System.out.println("[IuserService] queryUserInfo.");
    }
}
