package io.github.shengdoupi.springframework.context.event;

import io.github.shengdoupi.springframework.context.ApplicationEvent;
import io.github.shengdoupi.springframework.context.ApplicationListener;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    
    @Override
    public void multicastEvent(ApplicationEvent applicationEvent) {
        for (ApplicationListener applicationListener : getApplicationListeners(applicationEvent)) {
            applicationListener.onApplicationEvent(applicationEvent);
        }
    }
}
