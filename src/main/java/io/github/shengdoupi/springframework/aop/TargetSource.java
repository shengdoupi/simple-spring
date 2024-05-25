package io.github.shengdoupi.springframework.aop;

/**
 * @author zhoukehh
 * @date 2024/5/18
 * @description Is used to obtain the target of an AOP invocation.
 */
public class TargetSource {
    
    private final Object target;
    
    public TargetSource(Object target) {
        this.target = target;
    }
    
    /**
     * Get the class of a target.
     *
     * @return
     */
    public Class<?> getTargetClass() {
        return target.getClass();
    }
    
    /**
     * Get the target object.
     *
     * @return
     */
    public Object getTarget() {
        return target;
    }
}
