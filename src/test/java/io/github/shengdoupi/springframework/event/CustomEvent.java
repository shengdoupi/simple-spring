package io.github.shengdoupi.springframework.event;

import io.github.shengdoupi.springframework.context.ApplicationContext;
import io.github.shengdoupi.springframework.context.ApplicationEvent;
import io.github.shengdoupi.springframework.context.event.ApplicationContextEvent;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description
 */
public class CustomEvent extends ApplicationContextEvent {
    
    private Long id;
    
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(ApplicationContext source, Long id) {
        super(source);
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
