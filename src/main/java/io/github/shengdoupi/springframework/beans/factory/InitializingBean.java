package io.github.shengdoupi.springframework.beans.factory;

/**
 * @author zhoukehh
 * @date 2024/4/30
 * @description Interface to be implemented by beans that need react once all properties has been set.
 */
public interface InitializingBean {
    
    /**
     * Invoked by bean factory after properties set.
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
    
}
