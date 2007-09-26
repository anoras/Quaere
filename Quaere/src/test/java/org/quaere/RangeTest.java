package org.quaere;

import org.junit.Test;

import java.util.Iterator;
import java.math.BigInteger;
import java.math.BigDecimal;

import junit.framework.Assert;

public class RangeTest {
    @Test
    public void canCreateByteRange() {
        Range<Byte> r = new Range<Byte>((byte) 0, (byte) 10);
        Iterator<Byte> riter = r.iterator();
        for (Byte i = 0; i <= 10; i++) Assert.assertEquals(i, riter.next());
    }
    @Test
    public void canCreateShortRange() {
        Range<Short> r = new Range<Short>((short) 0, (short) 10);
        Iterator<Short> riter = r.iterator();
        for (Short i = 0; i <= 10; i++) Assert.assertEquals(i, riter.next());
    }
    @Test
    public void canCreateIntegerRange() {
        Range<Integer> r = new Range<Integer>(0, 10);
        Iterator<Integer> riter = r.iterator();
        for (Integer i = 0; i <= 10; i++) Assert.assertEquals(i, riter.next());
    }
    @Test
    public void canCreateLongRange() {
        Range<Long> r = new Range<Long>(0L, 10L);
        Iterator<Long> riter = r.iterator();
        for (Long i = 0L; i <= 10L; i++) Assert.assertEquals(i, riter.next());
    }
    @Test
    public void canCreateFloatRange() {
        Range<Float> r = new Range<Float>(0.4F, 10F);
        Iterator<Float> riter = r.iterator();
        for (Float i = 0.4F; i <= 10L; i++) Assert.assertEquals(i, riter.next());
    }
    @Test
    public void canCreateDoubleRange() {
        Range<Double> r = new Range<Double>(0.4D, 10D);
        Iterator<Double> riter = r.iterator();
        for (Double i = 0.4D; i <= 10D; i++) Assert.assertEquals(i, riter.next());
    }
    @Test
    public void canCreateBigIntegerRange() {
        Range<BigInteger> r = new Range<BigInteger>(BigInteger.ZERO, BigInteger.TEN);
        Iterator<BigInteger> riter = r.iterator();
        for (Long i = 0L; i <= 10L; i++) {
            Assert.assertEquals(BigInteger.valueOf(i), riter.next());
        }
    }
    @Test
    public void canCreateBigDecimalRange() {
        Range<BigDecimal> r = new Range<BigDecimal>(BigDecimal.valueOf(0.4D), BigDecimal.TEN);
        Iterator<BigDecimal> riter = r.iterator();
        for (Double i = 0.4; i <= 10D; i++) {
            Assert.assertEquals(BigDecimal.valueOf(i), riter.next());
        }
    }
    @Test
    public void rangeEndsAtEnd() {
        Range<Integer> r = new Range<Integer>(0, 1);
        Iterator<Integer> riter = r.iterator();
        riter.next();
        riter.next();
        Assert.assertFalse(riter.hasNext());
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void arrayIndexOutOfBoundsExceptionIsThrownWhenIteratorPassesEndOfRange() {
        Range<Integer> r = new Range<Integer>(0, 1);
        Iterator<Integer> riter = r.iterator();
        riter.next();
        riter.next();
        riter.next();
    }
}
