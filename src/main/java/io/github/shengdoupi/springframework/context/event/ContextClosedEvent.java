package io.github.shengdoupi.springframework.context.event;

import io.github.shengdoupi.springframework.context.ApplicationContext;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description Event raised when an ApplicationContext is closed.
 */
public class ContextClosedEvent extends ApplicationContextEvent{
    
    /**
     * Create a ContextClosedEvent.
     * @param source
     */
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
