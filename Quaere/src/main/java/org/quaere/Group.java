package org.quaere;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private final Object key;
    private final List<Object> group;

    public Group(Object key) {
        this.key = key;
        group = new ArrayList<Object>();
    }

    public Group(Object key, List<Object> values) {
        this.key = key;
        this.group = values;
    }
    public List<Object> getGroup() {
        return group;
    }

    public Object getKey() {
        return key;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group1 = (Group) o;

        if (group != null ? !group.equals(group1.group) : group1.group != null) return false;
        if (key != null ? !key.equals(group1.key) : group1.key != null) return false;

        return true;
    }
    public int hashCode() {
        int result;
        result = (key != null ? key.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
    public String toString() {
        return String.format("%s: %s", key, group);
    }
}
