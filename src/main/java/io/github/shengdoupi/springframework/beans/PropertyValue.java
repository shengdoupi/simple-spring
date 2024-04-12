package io.github.shengdoupi.springframework.beans;

import com.sun.istack.internal.NotNull;

/**
 * @author zhoukehh
 * @date 2024/4/11
 * @description Property value.
 */
public class PropertyValue {
    private String name;
    
    @NotNull
    private Object value;
    
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Object getValue() {
        return value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
}
