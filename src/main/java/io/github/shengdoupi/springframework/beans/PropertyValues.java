package io.github.shengdoupi.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoukehh
 * @date 2024/4/11
 * @description Property value.
 */
public class PropertyValues {
    private List<PropertyValue> propertyValues = new ArrayList<>();
    
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }
    
    public PropertyValue[] getPropertyValues() {
        return this.propertyValues.toArray(new PropertyValue[0]);
    }
}
