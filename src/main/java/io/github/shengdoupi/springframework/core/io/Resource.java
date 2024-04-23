package io.github.shengdoupi.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description Resource
 */
public interface Resource {
    
    /**
     * Get resource input stream.
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
