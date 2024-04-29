package io.github.shengdoupi.springframework.context.support;

/**
 * @author zhoukehh
 * @date 2024/4/27
 * @description
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;
    
    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }
    
    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }
    
    @Override
    public String[] getConfigLocations() {
        return this.configLocations;
    }
}
