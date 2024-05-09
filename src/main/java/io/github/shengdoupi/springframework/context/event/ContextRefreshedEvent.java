package io.github.shengdoupi.springframework.context.event;

import io.github.shengdoupi.springframework.context.ApplicationContext;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description Event raised when an ApplicationEvent get initialized or refreshed.
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    
    /**
     * Create a new ContextRefreshedEvent.
     * @param source
     */
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
