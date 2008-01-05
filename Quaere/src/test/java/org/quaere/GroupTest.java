package org.quaere;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GroupTest {
    @Test
    public void canConvertMapToGroup() {
        Map<Object, List<Object>> groupMap = new LinkedHashMap<Object, List<Object>>();
        List<Object> g1 = Arrays.<Object>asList("One");
        List<Object> g2 = Arrays.<Object>asList("One", "Two");
        List<Object> g3 = Arrays.<Object>asList("One", "Two", "Three");
        groupMap.put(1, g1);
        groupMap.put(2, g2);
        groupMap.put(3, g3);
        List<Group> groups = Group.fromMap(groupMap);
        Assert.assertEquals(3, groups.size());
        Assert.assertEquals(new Group(1, g1), groups.get(0));
        Assert.assertEquals(new Group(2, g2), groups.get(1));
        Assert.assertEquals(new Group(3, g3), groups.get(2));
    }
}
