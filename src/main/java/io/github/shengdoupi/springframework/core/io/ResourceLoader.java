package io.github.shengdoupi.springframework.core.io;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description Resource loader.
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";
    
    /**
     * Get resource.
     * @param location
     * @return Resource.
     */
    Resource getResource(String location);
}
