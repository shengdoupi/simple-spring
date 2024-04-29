package io.github.shengdoupi.springframework.context;

import io.github.shengdoupi.springframework.beans.BeansException;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description Provides facilities to configure an application context in addition to the application context client methods
 * in ApplicationContext interface.
 * Configuration  and lifecycle methods are encapsulated here.
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    
    /**
     * Load or refresh the persistent representation of the configuration.
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
