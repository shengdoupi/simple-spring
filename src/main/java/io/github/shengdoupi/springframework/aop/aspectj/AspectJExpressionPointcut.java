package io.github.shengdoupi.springframework.aop.aspectj;

import io.github.shengdoupi.springframework.aop.ClassFilter;
import io.github.shengdoupi.springframework.aop.MethodMatcher;
import io.github.shengdoupi.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhoukehh
 * @date 2024/5/14
 * @description A PointCut implementation, that uses AspectJ waver to evaluate a pointcut expression.
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
    
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<>();
    
    private final PointcutExpression pointcutExpression;
    
    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser =
                PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
                        SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }
    
    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    }
    
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }
    
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
    
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }
    
    @Override
    public boolean matches(Method method, Class<?> targetClazz) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }
}
