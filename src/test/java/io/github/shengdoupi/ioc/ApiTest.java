package io.github.shengdoupi.ioc;

import org.junit.Test;

/**
 * @author zhoukehh
 * @date 2024/3/21
 * @description
 */
public class ApiTest {
    @Test
    public void bean_test() {
        // 1.初始化 BeanFactory
        BeanFactory factory = new BeanFactory();
        // 2.注册 Bean
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        factory.registerBeanDefinition("person", beanDefinition);
        // 3.获取 Bean
        Person person = (Person) factory.getBean("person");
        person.sayHello();
    }
}
