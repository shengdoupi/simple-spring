package io.github.shengdoupi.springframework.beans;

import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.config.BeanReference;
import io.github.shengdoupi.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author zhoukehh
 * @date 2024/3/21
 * @description
 */
public class ApiTest {
    @Test
    public void bean_test() {
        // 0.初始化Bean工厂对象
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 1.初始化BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        // 2.注册BeanDefinition到Bean工厂
        beanFactory.registerBeanDefinition("person", beanDefinition);
        // 3.获取Bean
        Person bean = (Person) beanFactory.getBean("person");
        // 4.测试Bean
        bean.sayHello();
    }
    
    @Test
    public void bean_test_with_args() {
        // 0.初始化Bean工厂对象
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 1.初始化BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        // 2.注册BeanDefinition到Bean工厂
        beanFactory.registerBeanDefinition("person", beanDefinition);
        // 3.获取Bean
        Person bean = (Person) beanFactory.getBean("person", "Alice");
        // 4.测试Bean
        bean.sayHello();
    }
    
    
    @Test
    public void populate_bean_test_1() {
        // 0.初始化Bean工厂对象
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 1.注册BeanDefinition
        BeanDefinition userDaoDefinition = new BeanDefinition(UserDao.class);
        beanFactory.registerBeanDefinition("userDao", userDaoDefinition);
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue = new PropertyValue("userDao", new BeanReference() {
            @Override
            public String getBeanName() {
                return "userDao";
            }
        });
        propertyValues.addPropertyValue(propertyValue);
        BeanDefinition userServiceDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", userServiceDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("01");
    }
}
