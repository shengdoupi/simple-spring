package io.github.shengdoupi.springframework.aop.framework;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description A factory to get a proxy instance through configuration of cglib or jdk.
 */
public class ProxyFactory extends AdvisedSupport{
    
    public Object getProxy() {
        return createAopProxy().getProxy();
    }
    
    public AopProxy createAopProxy() {
        if (this.isProxyTargetClass()) {
            return new CglibAopProxy(this);
        }
        return new JdkDynamicAopProxy(this);
    }
}
