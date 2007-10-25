package org.quaere;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

public class NumberRange<T extends Number> implements Iterable<T> {
    private final T from;
    private final T to;

    public NumberRange(T from, T to) {
        this.from = from;
        this.to = to;
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private T current = from;
            public boolean hasNext() {
                if (current instanceof Byte) {
                    Byte castedCurrent = (Byte) current;
                    Byte castedTo = (Byte) to;
                    return castedCurrent <= castedTo;
                } else if (current instanceof Short) {
                    Short castedCurrent = (Short) current;
                    Short castedTo = (Short) to;
                    return castedCurrent <= castedTo;
                } else if (current instanceof Integer) {
                    Integer castedCurrent = (Integer) current;
                    Integer castedTo = (Integer) to;
                    return castedCurrent <= castedTo;
                } else if (current instanceof Long) {
                    Long castedCurrent = (Long) current;
                    Long castedTo = (Long) to;
                    return castedCurrent <= castedTo;
                } else if (current instanceof Float) {
                    Float castedCurrent = (Float) current;
                    Float castedTo = (Float) to;
                    return castedCurrent <= castedTo;
                } else if (current instanceof Double) {
                    Double castedCurrent = (Double) current;
                    Double castedTo = (Double) to;
                    return castedCurrent <= castedTo;
                } else if (current instanceof BigInteger) {
                    BigInteger castedCurrent = (BigInteger) current;
                    BigInteger castedTo = (BigInteger) to;
                    return castedCurrent.compareTo(castedTo) <= 0;
                } else if (current instanceof BigDecimal) {
                    BigDecimal castedCurrent = (BigDecimal) current;
                    BigDecimal castedTo = (BigDecimal) to;
                    return castedCurrent.compareTo(castedTo) <= 0;
                } else {
                    throw new UnsupportedOperationException();
                }
            }
            @SuppressWarnings({"unchecked"})
            public T next() {
                if (!hasNext()) throw new ArrayIndexOutOfBoundsException();
                T retVal = current;
                if (current instanceof Byte) {
                    Byte castedCurrent = (Byte) current;
                    castedCurrent++;
                    current = (T) castedCurrent;
                } else if (current instanceof Short) {
                    Short castedCurrent = (Short) current;
                    castedCurrent++;
                    current = (T) castedCurrent;
                } else if (current instanceof Integer) {
                    Integer castedCurrent = (Integer) current;
                    castedCurrent++;
                    current = (T) castedCurrent;
                } else if (current instanceof Long) {
                    Long castedCurrent = (Long) current;
                    castedCurrent++;
                    current = (T) castedCurrent;
                } else if (current instanceof Float) {
                    Float castedCurrent = (Float) current;
                    castedCurrent++;
                    current = (T) castedCurrent;
                } else if (current instanceof Double) {
                    Double castedCurrent = (Double) current;
                    castedCurrent++;
                    current = (T) castedCurrent;
                } else if (current instanceof BigInteger) {
                    BigInteger castedCurrent = (BigInteger) current;
                    castedCurrent = castedCurrent.add(BigInteger.ONE);
                    current = (T) castedCurrent;
                } else if (current instanceof BigDecimal) {
                    BigDecimal castedCurrent = (BigDecimal) current;
                    castedCurrent = castedCurrent.add(BigDecimal.ONE);
                    current = (T) castedCurrent;
                } else {
                    throw new UnsupportedOperationException();
                }
                return retVal;
            }
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove elements from a range.");
            }
        };
    }
}
