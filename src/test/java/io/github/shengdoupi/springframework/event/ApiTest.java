package io.github.shengdoupi.springframework.event;

import io.github.shengdoupi.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author zhoukehh
 * @date 2024/5/9
 * @description
 */
public class ApiTest {
    
    @Test
    public void application_event_test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-application-event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1l));
        applicationContext.registerShutDownHook();
    }
}
