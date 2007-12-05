package org.quaere;

import org.junit.Test;

import java.util.Iterator;
import java.math.BigInteger;
import java.math.BigDecimal;

import junit.framework.Assert;

public class RangeTest {
    @Test
    public void canCreateByteRange() {
        NumberRange<Byte> r = new NumberRange<Byte>((byte) 0, (byte) 10);
        Iterator<Byte> riter = r.iterator();
        for (Byte i = 0; i <= 10; i++) {
            Assert.assertEquals(i, riter.next());
        }
    }
    @Test
    public void canCreateShortRange() {
        NumberRange<Short> r = new NumberRange<Short>((short) 0, (short) 10);
        Iterator<Short> riter = r.iterator();
        for (Short i = 0; i <= 10; i++) {
            Assert.assertEquals(i, riter.next());
        }
    }
    @Test
    public void canCreateIntegerRange() {
        NumberRange<Integer> r = new NumberRange<Integer>(0, 10);
        Iterator<Integer> riter = r.iterator();
        for (Integer i = 0; i <= 10; i++) {
            Assert.assertEquals(i, riter.next());
        }
    }
    @Test
    public void canCreateLongRange() {
        NumberRange<Long> r = new NumberRange<Long>(0L, 10L);
        Iterator<Long> riter = r.iterator();
        for (Long i = 0L; i <= 10L; i++) {
            Assert.assertEquals(i, riter.next());
        }
    }
    @Test
    public void canCreateFloatRange() {
        NumberRange<Float> r = new NumberRange<Float>(0.4F, 10F);
        Iterator<Float> riter = r.iterator();
        for (Float i = 0.4F; i <= 10L; i++) {
            Assert.assertEquals(i, riter.next());
        }
    }
    @Test
    public void canCreateDoubleRange() {
        NumberRange<Double> r = new NumberRange<Double>(0.4D, 10D);
        Iterator<Double> riter = r.iterator();
        for (Double i = 0.4D; i <= 10D; i++) {
            Assert.assertEquals(i, riter.next());
        }
    }
    @Test
    public void canCreateBigIntegerRange() {
        NumberRange<BigInteger> r = new NumberRange<BigInteger>(BigInteger.ZERO, BigInteger.TEN);
        Iterator<BigInteger> riter = r.iterator();
        for (Long i = 0L; i <= 10L; i++) {
            Assert.assertEquals(BigInteger.valueOf(i), riter.next());
        }
    }
    @Test
    public void canCreateBigDecimalRange() {
        NumberRange<BigDecimal> r = new NumberRange<BigDecimal>(BigDecimal.valueOf(0.4D), BigDecimal.TEN);
        Iterator<BigDecimal> riter = r.iterator();
        for (Double i = 0.4; i <= 10D; i++) {
            Assert.assertEquals(BigDecimal.valueOf(i), riter.next());
        }
    }
    @Test
    public void rangeEndsAtEnd() {
        NumberRange<Integer> r = new NumberRange<Integer>(0, 1);
        Iterator<Integer> riter = r.iterator();
        riter.next();
        riter.next();
        Assert.assertFalse(riter.hasNext());
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void arrayIndexOutOfBoundsExceptionIsThrownWhenIteratorPassesEndOfRange() {
        NumberRange<Integer> r = new NumberRange<Integer>(0, 1);
        Iterator<Integer> riter = r.iterator();
        riter.next();
        riter.next();
        riter.next();
    }

}
