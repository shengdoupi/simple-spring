package io.github.shengdoupi.springframework.beans.factory;

import java.util.Map;

/**
 * @author zhoukehh
 * @date 2024/4/26
 * @description Listable bean factory, which can enumerate all bean instances in factory.
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * Return bean instances that match the given object type, including subclasses.
     * @param type
     * @return
     * @param <T>
     */
    <T> Map<String, T> getBeansOfType(Class<T> type);
    
    /**
     * Return the names of all beans which defined in this factory.
     * @return
     */
    String[] getBeanDefinitionNames();
}
