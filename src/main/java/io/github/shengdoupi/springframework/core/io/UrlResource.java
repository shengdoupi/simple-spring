package io.github.shengdoupi.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description Url resource.
 */
public class UrlResource implements Resource {
    
    private final URL url;
    
    public UrlResource(URL url) {
        this.url = url;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw ex;
        }
    }
}
