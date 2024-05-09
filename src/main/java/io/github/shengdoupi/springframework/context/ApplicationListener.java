package io.github.shengdoupi.springframework.context;

import java.util.EventListener;

/**
 * @author zhoukehh
 * @date 2024/5/7
 * @description Interface to be implemented by all application listeners.
 * Based on the standard interface @EventListener for the Observer design pattern.
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {
    
    /**
     * Handle an application event.
     * @param event
     */
    void onApplicationEvent(E event);
}
