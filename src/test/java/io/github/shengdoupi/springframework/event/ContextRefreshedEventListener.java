package io.github.shengdoupi.springframework.event;

import io.github.shengdoupi.springframework.context.ApplicationEvent;
import io.github.shengdoupi.springframework.context.ApplicationListener;
import io.github.shengdoupi.springframework.context.event.ContextRefreshedEvent;

/**
 * @author zhoukehh
 * @date 2024/5/9
 * @description
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Receive a context refreshed event.");
    }
}
