package io.github.shengdoupi.springframework.beans;

import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
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
}
