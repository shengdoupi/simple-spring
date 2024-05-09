package io.github.shengdoupi.springframework.context.event;

import io.github.shengdoupi.springframework.context.ApplicationContext;
import io.github.shengdoupi.springframework.context.ApplicationEvent;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description Base class for events raised for an ApplicationContext.
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }
    
    /**
     * Get ApplicationContext that the event was raised for.
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
