package org.quaere.quaere4objects;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final Object key;
    private final List<Object> group = new ArrayList<Object>();

    public Group(Object key) {
        this.key = key;
    }

    public List<Object> getGroup() {
        return group;
    }

    public Object getKey() {
        return key;
    }
}
