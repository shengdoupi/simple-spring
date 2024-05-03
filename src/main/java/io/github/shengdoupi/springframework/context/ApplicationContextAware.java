package io.github.shengdoupi.springframework.context;

import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.Aware;

/**
 * @author zhoukehh
 * @date 2024/5/2
 * @description Interface to be implemented by a object that want to be notified of the
 * application context it runs in.
 */
public interface ApplicationContextAware extends Aware {
    
    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * Invoked after properties population but before an init callback such as
     * afterPropertiesSet() or a custom init-method. Invoked after ResourceLoadedAware.
     * @param applicationContext
     * @throws BeansException
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
