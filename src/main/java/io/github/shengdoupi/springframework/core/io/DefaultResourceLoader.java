package io.github.shengdoupi.springframework.core.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description Default resource loader.
 */
public class DefaultResourceLoader implements ResourceLoader{
    
    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException ex) {
                return new FileSystemResource(location);
            }
        }
    }
}
