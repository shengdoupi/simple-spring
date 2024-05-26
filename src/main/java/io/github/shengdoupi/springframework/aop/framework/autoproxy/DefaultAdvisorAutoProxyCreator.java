package io.github.shengdoupi.springframework.aop.framework.autoproxy;

import io.github.shengdoupi.springframework.aop.Advisor;
import io.github.shengdoupi.springframework.aop.Pointcut;
import io.github.shengdoupi.springframework.aop.PointcutAdvisor;
import io.github.shengdoupi.springframework.aop.TargetSource;
import io.github.shengdoupi.springframework.aop.aspectj.AspectJPointcutAdvisor;
import io.github.shengdoupi.springframework.aop.framework.AdvisedSupport;
import io.github.shengdoupi.springframework.aop.framework.JdkDynamicAopProxy;
import io.github.shengdoupi.springframework.aop.framework.ProxyFactory;
import io.github.shengdoupi.springframework.beans.BeansException;
import io.github.shengdoupi.springframework.beans.factory.BeanFactory;
import io.github.shengdoupi.springframework.beans.factory.BeanFactoryAware;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import io.github.shengdoupi.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.aopalliance.aop.Advice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/5/25
 * @description BeanPostProcessor implementation that creates proxies based on all advices in the current factory.
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    
    /**
     * Current bean factory.
     */
    private ConfigurableListableBeanFactory beanFactory;
    
    /**
     * Create a possible proxy bean for a bean definition based on all PointcutAdvisor in current bean factory.
     * @param beanClass
     * @param beanName
     * @return Possible proxy object.
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        // The Advice and Pointcut related beans should not continue;
        if (isInfrastructureClass(beanClass)) {
            return null;
        }
        // Get targetSource.
        TargetSource targetSource = null;
        try {
            targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Get all beans of PointcutAdvisor.
        Collection<AspectJPointcutAdvisor> advisorBeans = beanFactory.getBeansOfType(AspectJPointcutAdvisor.class).values();
        ProxyFactory proxyFactory = new ProxyFactory();
        List<Object> advisors = new ArrayList<>();
        for (AspectJPointcutAdvisor advisor : advisorBeans) {
            Pointcut pointcut = advisor.getPointcut();
            if (!pointcut.getClassFilter().matches(beanClass)) {
                continue;
            }
            advisors.add(advisor);
        }
        if (!advisors.isEmpty()) {
            proxyFactory.setAdvisors(advisors);
            proxyFactory.setTargetSource(targetSource);
            return proxyFactory.getProxy();
        }
        return null;
    }
    
    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass)
                || Advisor.class.isAssignableFrom(beanClass);
    }
    
    @Override
    public Object postProcessAfterInstantiation(Object bean, String beanName) {
        return bean;
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
}
