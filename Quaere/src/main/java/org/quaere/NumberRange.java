package org.quaere;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

public class NumberRange<T extends Number> implements Iterable<T> {
    private final T from;
    private final T to;
    private final int inc;

    public NumberRange(T from, T to) {
        this.from = from;
        this.to = to;
        inc = Comparer.compare(to,from);
    }
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private T current = from;
            public boolean hasNext() {
                if (current instanceof Byte) {
                    Byte castedCurrent = (Byte) current;
                    Byte castedTo = (Byte) to;
                    if (inc>0) {
                        return castedCurrent <= castedTo;
                    } else {
                        return castedCurrent >= castedTo;
                    }
                } else if (current instanceof Short) {
                    Short castedCurrent = (Short) current;
                    Short castedTo = (Short) to;
                    if (inc>0) {
                        return castedCurrent <= castedTo;
                    } else {
                        return castedCurrent >= castedTo;
                    }
                } else if (current instanceof Integer) {
                    Integer castedCurrent = (Integer) current;
                    Integer castedTo = (Integer) to;
                    if (inc>0) {
                        return castedCurrent <= castedTo;
                    } else {
                        return castedCurrent >= castedTo;
                    }
                } else if (current instanceof Long) {
                    Long castedCurrent = (Long) current;
                    Long castedTo = (Long) to;
                    if (inc>0) {
                        return castedCurrent <= castedTo;
                    } else {
                        return castedCurrent >= castedTo;
                    }
                } else if (current instanceof Float) {
                    Float castedCurrent = (Float) current;
                    Float castedTo = (Float) to;
                    if (inc>0) {
                        return castedCurrent <= castedTo;
                    } else {
                        return castedCurrent >= castedTo;
                    }
                } else if (current instanceof Double) {
                    Double castedCurrent = (Double) current;
                    Double castedTo = (Double) to;
                    if (inc>0) {
                        return castedCurrent <= castedTo;
                    } else {
                        return castedCurrent >= castedTo;
                    }
                } else if (current instanceof BigInteger) {
                    BigInteger castedCurrent = (BigInteger) current;
                    BigInteger castedTo = (BigInteger) to;
                    if (inc>0) {
                        return castedCurrent.compareTo(castedTo) <= 0;
                    } else {
                        return castedCurrent.compareTo(castedTo) >= 0;
                    }
                } else if (current instanceof BigDecimal) {
                    BigDecimal castedCurrent = (BigDecimal) current;
                    BigDecimal castedTo = (BigDecimal) to;
                    if (inc>0) {
                        return castedCurrent.compareTo(castedTo) <= 0;
                    } else {
                        return castedCurrent.compareTo(castedTo) >= 0;
                    }
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
                    if (inc>0) {
                        castedCurrent++;
                    } else {
                        castedCurrent--;
                    }
                    current = (T) castedCurrent;
                } else if (current instanceof Short) {
                    Short castedCurrent = (Short) current;
                    if (inc>0) {
                        castedCurrent++;
                    } else {
                        castedCurrent--;
                    }
                    current = (T) castedCurrent;
                } else if (current instanceof Integer) {
                    Integer castedCurrent = (Integer) current;
                    if (inc>0) {
                        castedCurrent++;
                    } else {
                        castedCurrent--;
                    }
                    current = (T) castedCurrent;
                } else if (current instanceof Long) {
                    Long castedCurrent = (Long) current;
                    if (inc>0) {
                        castedCurrent++;
                    } else {
                        castedCurrent--;
                    }
                    current = (T) castedCurrent;
                } else if (current instanceof Float) {
                    Float castedCurrent = (Float) current;
                    if (inc>0) {
                        castedCurrent++;
                    } else {
                        castedCurrent--;
                    }
                    current = (T) castedCurrent;
                } else if (current instanceof Double) {
                    Double castedCurrent = (Double) current;
                    if (inc>0) {
                        castedCurrent++;
                    } else {
                        castedCurrent--;
                    }
                    current = (T) castedCurrent;
                } else if (current instanceof BigInteger) {
                    BigInteger castedCurrent = (BigInteger) current;
                    if (inc>0){
                        castedCurrent = castedCurrent.add(BigInteger.ONE);
                    } else {
                        castedCurrent = castedCurrent.subtract(BigInteger.ONE);
                    }
                    current = (T) castedCurrent;
                } else if (current instanceof BigDecimal) {
                    BigDecimal castedCurrent = (BigDecimal) current;
                    if (inc>0){
                        castedCurrent = castedCurrent.add(BigDecimal.ONE);
                    } else {
                        castedCurrent = castedCurrent.subtract(BigDecimal.ONE);
                    }
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
