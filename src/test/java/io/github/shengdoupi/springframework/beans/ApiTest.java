package io.github.shengdoupi.springframework.beans;

import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.BeanDefinition;
import io.github.shengdoupi.springframework.beans.factory.config.BeanReference;
import io.github.shengdoupi.springframework.beans.factory.support.DefaultListableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.support.XmlBeanDefinitionReader;
import io.github.shengdoupi.springframework.context.ApplicationContext;
import io.github.shengdoupi.springframework.context.support.ClassPathXmlApplicationContext;
import io.github.shengdoupi.springframework.core.io.DefaultResourceLoader;
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
    
    @Test
    public void xml_resource_loader() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        
        // 2.读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.setResourceLoader(new DefaultResourceLoader());
        reader.loadBeanDefinitions("classpath:spring.xml");
        
        // 3.获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo("01");
    }
    
    @Test
    public void application_context_test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-application-context.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo("01");
    }
    
    @Test
    public void init_and_destroy_method_test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-application-context.xml");
        applicationContext.registerShutDownHook();
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo("01");
    }
    
    @Test
    public void aware_test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-application-context.xml");
        applicationContext.registerShutDownHook();
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo("01");
        System.out.println("ClassLoader: " + userService.getClassLoader());
        System.out.println("BeanFactory: " + userService.getBeanFactory());
        System.out.println("BeanName: " + userService.getBeanName());
        System.out.println("ApplicationContext: " + userService.getApplicationContext());
    }
    
    @Test
    public void scope_test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-factory-bean.xml");
        applicationContext.registerShutDownHook();
        // switch scope from singleton to prototype
        UserService userService1 = (UserService) applicationContext.getBean("userService");
        UserService userService2 = (UserService) applicationContext.getBean("userService");
        System.out.println(userService1.hashCode());
        System.out.println(userService2.hashCode());
    }
    
    @Test
    public void factory_bean_test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-factory-bean.xml");
        applicationContext.registerShutDownHook();
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.queryUserInfo("01");
    }
}
