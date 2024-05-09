package io.github.shengdoupi.springframework.event;

import io.github.shengdoupi.springframework.context.ApplicationListener;
import io.github.shengdoupi.springframework.context.event.ContextClosedEvent;

/**
 * @author zhoukehh
 * @date 2024/5/9
 * @description
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("Receive a context closed event.");
    }
}
