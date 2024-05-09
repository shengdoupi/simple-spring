package io.github.shengdoupi.springframework.event;

import io.github.shengdoupi.springframework.context.ApplicationEvent;
import io.github.shengdoupi.springframework.context.ApplicationListener;

/**
 * @author zhoukehh
 * @date 2024/5/9
 * @description
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("Receive a custom event, id: " + event.getId());
    }
}
