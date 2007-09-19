package org.quaere;

import java.util.Map;
import java.util.HashMap;

public class Variant {
    private final Map<String,Object> properties=new HashMap<String, Object>();
    public Object get(String propertyName) {
        return properties.get(propertyName);
    }
    public void add(String propertyName, Object value) {
        properties.put(propertyName, value);
    }
}
