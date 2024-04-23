package io.github.shengdoupi.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description Class path resource.
 */
public class ClassPathResource implements Resource {
    String path;
    
    ClassLoader classLoader;
    
    public ClassPathResource(String path) {
        this(path, null);
    }
    
    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        if (classLoader == null) {
            this.classLoader = ClassLoader.getSystemClassLoader();
        } else {
            this.classLoader = classLoader;
        }
    }
    
    
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if (null == is) {
            throw new FileNotFoundException(this.path + "has no file found.");
        }
        return is;
    }
}
