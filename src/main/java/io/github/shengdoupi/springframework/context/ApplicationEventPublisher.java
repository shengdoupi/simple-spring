package io.github.shengdoupi.springframework.context;

/**
 * @author zhoukehh
 * @date 2024/5/8
 * @description Interface that encapsulates event publish functionality.
 */
public interface ApplicationEventPublisher {
    
    /**
     * Notify all matching listeners registered with this application of an event.
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
