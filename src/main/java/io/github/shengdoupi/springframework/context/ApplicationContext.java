package io.github.shengdoupi.springframework.context;

import io.github.shengdoupi.springframework.beans.factory.HierarchicalBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.ListableBeanFactory;
import io.github.shengdoupi.springframework.core.io.ResourceLoader;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description Central interface to provide configuration for an application.
 * An application context provides:
 * 1. Bean factory methods for accessing application components.
 * 2. The ability to load file resources.
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
