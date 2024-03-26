package io.github.shengdoupi.springframework.beans;

/**
 * @author zhoukehh
 * @date 2024/3/27
 * @description Beans exception.
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg) {
        super(msg);
    }
    
    public BeansException(String msg, Throwable t) {
        super(msg, t);
    }
}
