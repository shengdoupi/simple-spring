package io.github.shengdoupi.springframework.context.event;

import io.github.shengdoupi.springframework.context.ApplicationEvent;
import io.github.shengdoupi.springframework.context.ApplicationListener;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description Interface to be implemented by objects that can register and manage ApplicationListener objects
 * and publish events to them.
 */
public interface ApplicationEventMulticaster {
    
    /**
     * Add a listener to be notified of all events.
     * @param applicationListener
     */
    void addApplicationListener(ApplicationListener<?> applicationListener);
    
    /**
     * Remove a listener from the notification list.
     * @param applicationListener
     */
    void removeApplicationListener(ApplicationListener<?> applicationListener);
    
    /**
     * Multicast the given application event to appropriate listeners.
     * @param applicationEvent
     */
    void multicastEvent(ApplicationEvent applicationEvent);
}
