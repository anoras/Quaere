package org.quaere;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FastConvertTest {
    protected static void convertAndCheck(final Object toConvert, final Class<?> toClass, final Object expected) {
        final Object converted = Convert.toType(toConvert, toClass);
        final String msg = String.format("Convert (%s)%s  to (%s)%s", toConvert.getClass().getSimpleName(), toConvert, toClass.getSimpleName(),expected);
        Assert.assertTrue(msg, toClass.isInstance(converted));
        if (toClass.equals(toConvert.getClass()))
            Assert.assertSame(msg, toConvert, converted);
        Assert.assertEquals(msg, expected, converted);
    }


    @Test
    public void canConvertByte() {
        convertAndCheck((byte) 4, Byte.class, (byte) 4);
        convertAndCheck((byte) 4, Short.class, (short) 4);
        convertAndCheck((byte) 4, Integer.class, 4);
        convertAndCheck((byte) 4, Long.class, 4L);
        convertAndCheck((byte) 4, Float.class, 4F);
        convertAndCheck((byte) 4, Double.class, 4D);
        convertAndCheck((byte) 4, BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck((byte) 4, BigDecimal.class, BigDecimal.valueOf(4L));
        convertAndCheck((byte) 'a', Character.class, 'a');
    }


    @Test
    public void canConvertShort() {
        convertAndCheck((short) 4, Byte.class, (byte) 4);
        convertAndCheck((short) 4, Short.class, (short) 4);
        convertAndCheck((short) 4, Integer.class, 4);
        convertAndCheck((short) 4, Long.class, 4L);
        convertAndCheck((short) 4, Float.class, 4F);
        convertAndCheck((short) 4, Double.class, 4D);
        convertAndCheck((short) 4, BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck((short) 4, BigDecimal.class, BigDecimal.valueOf(4L));
        convertAndCheck((short) 'a', Character.class, 'a');
    }


    @Test
    public void canConvertInteger() {
        convertAndCheck(4, Byte.class, (byte) 4);
        convertAndCheck(4, Short.class, (short) 4);
        convertAndCheck(4, Integer.class, 4);
        convertAndCheck(4, Long.class, 4L);
        convertAndCheck(4, Float.class, 4F);
        convertAndCheck(4, Double.class, 4D);
        convertAndCheck(4, BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck(4, BigDecimal.class, BigDecimal.valueOf(4L));
        convertAndCheck((int) 'a', Character.class, 'a');
    }


    @Test
    public void canConvertLong() {
        convertAndCheck(4L, Byte.class, (byte) 4);
        convertAndCheck(4L, Short.class, (short) 4);
        convertAndCheck(4L, Integer.class, 4);
        convertAndCheck(4L, Long.class, 4L);
        convertAndCheck(4L, Float.class, 4F);
        convertAndCheck(4L, Double.class, 4D);
        convertAndCheck(4L, BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck(4L, BigDecimal.class, BigDecimal.valueOf(4L));
        convertAndCheck((long) 'a', Character.class, 'a');
    }


    @Test
    public void canConvertFloat() {
        convertAndCheck(4.14F, Byte.class, (byte) 4);
        convertAndCheck(4.14F, Short.class, (short) 4);
        convertAndCheck(4.14F, Integer.class, 4);
        convertAndCheck(4.14F, Long.class, 4L);
        convertAndCheck(4.14F, Float.class, 4.14F);
        convertAndCheck(4.14F, Double.class, (double) 4.14F);
        convertAndCheck(4.14F, BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck(4.14F, BigDecimal.class, new BigDecimal(4.14F));
        convertAndCheck((float) 'a', Character.class, 'a');
    }


    @Test
    public void canConvertDouble() {
        convertAndCheck(4.14D, Byte.class, (byte) 4);
        convertAndCheck(4.14D, Short.class, (short) 4);
        convertAndCheck(4.14D, Integer.class, 4);
        convertAndCheck(4.14D, Long.class, 4L);
        convertAndCheck(4.14D, Float.class, 4.14F);
        convertAndCheck(4.14D, Double.class, 4.14D);
        convertAndCheck(4.14D, BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck(4.14D, BigDecimal.class, new BigDecimal(4.14D));
        convertAndCheck((double) 'a', Character.class, 'a');
    }


    @Test
    public void canConvertBigInteger() {
        convertAndCheck(BigInteger.valueOf(4L), Byte.class, (byte) 4);
        convertAndCheck(BigInteger.valueOf(4L), Short.class, (short) 4);
        convertAndCheck(BigInteger.valueOf(4L), Integer.class, 4);
        convertAndCheck(BigInteger.valueOf(4L), Long.class, 4L);
        convertAndCheck(BigInteger.valueOf(4L), Float.class, 4F);
        convertAndCheck(BigInteger.valueOf(4L), Double.class, 4D);
        convertAndCheck(BigInteger.valueOf(4L), BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck(BigInteger.valueOf(4L), BigDecimal.class, BigDecimal.valueOf(4L));
        convertAndCheck(BigInteger.valueOf((long) 'a'), Character.class, 'a');
    }


    @Test
    public void canConvertBigDecimal() {
        convertAndCheck(BigDecimal.valueOf(4.14D), Byte.class, (byte) 4);
        convertAndCheck(BigDecimal.valueOf(4.14D), Short.class, (short) 4);
        convertAndCheck(BigDecimal.valueOf(4.14D), Integer.class, 4);
        convertAndCheck(BigDecimal.valueOf(4.14D), Long.class, 4L);
        convertAndCheck(BigDecimal.valueOf(4.14D), Float.class, BigDecimal.valueOf(4.14D).floatValue());
        convertAndCheck(BigDecimal.valueOf(4.14D), Double.class, BigDecimal.valueOf(4.14D).doubleValue());
        convertAndCheck(BigDecimal.valueOf(4.14D), BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck(BigDecimal.valueOf(4.14D), BigDecimal.class, BigDecimal.valueOf(4.14D));
        convertAndCheck(BigDecimal.valueOf((long) 'a'), Character.class, 'a');
    }


    @Test
    public void canConvertBoolean() {
        convertAndCheck(true, Byte.class, (byte) 1);
        convertAndCheck(false, Byte.class, (byte) 0);
        convertAndCheck(true, Short.class, (short) 1);
        convertAndCheck(false, Short.class, (short) 0);
        convertAndCheck(true, Integer.class, 1);
        convertAndCheck(false, Integer.class, 0);
        convertAndCheck(true, Long.class, 1L);
        convertAndCheck(false, Long.class, 0L);
        convertAndCheck(true, Float.class, 1F);
        convertAndCheck(false, Float.class, 0F);
        convertAndCheck(true, Double.class, 1D);
        convertAndCheck(false, Double.class, 0D);
        convertAndCheck(true, BigInteger.class, BigInteger.valueOf(1L));
        convertAndCheck(false, BigInteger.class, BigInteger.valueOf(0));
        convertAndCheck(true, BigDecimal.class, BigDecimal.valueOf(1L));
        convertAndCheck(false, BigDecimal.class, BigDecimal.valueOf(0));
    }

    @Test
    public void canConvertString() {
        convertAndCheck("4", Byte.class, (byte) 4);
        convertAndCheck("4", Short.class, (short) 4);
        convertAndCheck("4", Integer.class, 4);
        convertAndCheck("4", Long.class, 4L);
        convertAndCheck("4", Float.class, 4F);
        convertAndCheck("4.14", Float.class, 4.14F);
        convertAndCheck("4", Double.class, 4D);
        convertAndCheck("4.14", Double.class, 4.14D);
        convertAndCheck("4", BigInteger.class, BigInteger.valueOf(4L));
        convertAndCheck("4", BigDecimal.class, BigDecimal.valueOf(4L));
        convertAndCheck("4.14", BigDecimal.class, BigDecimal.valueOf(4.14D));
    }

    @Test
    public void canConvertCharacter() {
        convertAndCheck('a', Byte.class, (byte) 'a');
        convertAndCheck('a', Short.class, (short) 'a');
        convertAndCheck('a', Integer.class, (int) 'a');
        convertAndCheck('a', Long.class, (long) 'a');
        convertAndCheck('a', Float.class, (float) 'a');
        convertAndCheck('a', Double.class, (double) 'a');
        convertAndCheck('a', BigInteger.class, BigInteger.valueOf((long) 'a'));
    }
}