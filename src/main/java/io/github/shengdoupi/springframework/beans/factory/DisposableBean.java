package io.github.shengdoupi.springframework.beans.factory;

/**
 * @author zhoukehh
 * @date 2024/4/30
 * @description Interface to be implemented by beans that need to release resources on destruction.
 */
public interface DisposableBean {
    
    /**
     * Invoked by bean factory on destruction of a bean.
     * @throws Exception
     */
    void destroy() throws Exception;
    
}
