package io.github.shengdoupi.springframework.context;

import java.util.EventObject;

/**
 * @author zhoukehh
 * @date 2024/5/7
 * @description Class to be extended by all application events.
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
