package io.github.shengdoupi.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author zhoukehh
 * @date 2024/4/20
 * @description File system resource.
 */
public class FileSystemResource implements Resource {
    private String path;
    
    private File file;
    
    private Path filePath;
    
    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
        this.filePath = this.file.toPath();
    }
    
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
        this.filePath = file.toPath();
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        try {
            return Files.newInputStream(filePath);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
