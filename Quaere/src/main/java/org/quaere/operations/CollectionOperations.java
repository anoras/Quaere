package org.quaere.operations;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.reflect.Array;

/**
 * @author mh14 @ jexp.de
 * @since 11.11.2007 10:27:05 (c) 2007 jexp.de
 */
public class CollectionOperations {
    // Conversion operators
    public static <T> T[] asArray(T[] tArray, Iterable<T> source) {
        List<T> asList = new ArrayList<T>();
        for (T elm : source) {
            asList.add(elm);
        }
        return asList.toArray(tArray);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T[] asArray(Iterable<T> source) {
        List<T> asList = new LinkedList<T>();
        for (T elm : source) {
            asList.add(elm);
        }
        if (asList.isEmpty()) return (T[]) asList.toArray();
        final Class<T> type = (Class<T>) asList.get(0).getClass();
        final T[] array = (T[]) Array.newInstance(type, asList.size());
        return asList.toArray(array);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> List<T> asList(Iterable<?> iterable) {
        List<T> asList = new LinkedList<T>();
        for (Object elm : iterable) {
            asList.add((T) elm); // todo convert to type ??
        }
        return asList;
    }
    public static <T> Iterable<T> ofClass(Class<T> clazz, Object... source) {
        return ofClass(clazz, Arrays.asList(source));
    }
    @SuppressWarnings({"unchecked"})
    public static <T> Iterable<T> ofClass(Class<T> clazz, Iterable<?> source) {
        List<T> result = new ArrayList<T>();
        for (Object obj : source) {
            if (obj != null && obj.getClass().equals(clazz)) {
                result.add((T) obj);
            }
        }
        return result;
    }

    // Ordering operators
    public static <T> Iterable<T> reverse(Iterable<T> sequence) {
        List<T> reversedList = new LinkedList<T>();
        for (T elm : sequence) {
            reversedList.add(0,elm);
        }
        return reversedList;
    }
    
}
