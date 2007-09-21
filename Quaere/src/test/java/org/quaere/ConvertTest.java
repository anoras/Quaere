package org.quaere;

import org.junit.Test;
import org.junit.Assert;

import java.math.BigInteger;
import java.math.BigDecimal;

public class ConvertTest {
    // Coerce from Byte
    @Test
    public void canCoerceByteToByte() {
        Object coerced = Convert.coerce((byte) 4, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceByteToShort() {
        Object coerced = Convert.coerce((byte) 4, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceByteToInteger() {
        Object coerced = Convert.coerce((byte) 4, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceByteToLong() {
        Object coerced = Convert.coerce((byte) 4, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceByteToFloat() {
        Object coerced = Convert.coerce((byte) 4, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4F, coerced);
    }
    @Test
    public void canCoerceByteToDouble() {
        Object coerced = Convert.coerce((byte) 4, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4D, coerced);
    }
    @Test
    public void canCoerceByteToBigInteger() {
        Object coerced = Convert.coerce((byte) 4, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceByteToBigDecimal() {
        Object coerced = Convert.coerce((byte) 4, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceByteToCharacter() {
        Object coerced = Convert.coerce((byte) 'a', Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }
    // Coerce from Short
    @Test
    public void canCoerceShortToByte() {
        Short b = 4;
        Object coerced = Convert.coerce(b, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceShortToShort() {
        Short b = 4;
        Object coerced = Convert.coerce(b, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceShortToInteger() {
        Short b = 4;
        Object coerced = Convert.coerce(b, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceShortToLong() {
        Short b = 4;
        Object coerced = Convert.coerce(b, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceShortToFloat() {
        Short b = 4;
        Object coerced = Convert.coerce(b, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4F, coerced);
    }
    @Test
    public void canCoerceShortToDouble() {
        Short b = 4;
        Object coerced = Convert.coerce(b, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4D, coerced);
    }
    @Test
    public void canCoerceShortToBigInteger() {
        Short b = 4;
        Object coerced = Convert.coerce(b, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceShortToBigDecimal() {
        Short b = 4;
        Object coerced = Convert.coerce(b, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceShortToCharacter() {
        Object coerced = Convert.coerce((short) 'a', Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }
    // Coerce from Integer
    @Test
    public void canCoerceIntegerToByte() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceIntegerToShort() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceIntegerToInteger() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceIntegerToLong() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceIntegerToFloat() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4F, coerced);
    }
    @Test
    public void canCoerceIntegerToDouble() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4D, coerced);
    }
    @Test
    public void canCoerceIntegerToBigInteger() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceIntegerToBigDecimal() {
        Integer b = 4;
        Object coerced = Convert.coerce(b, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceIntegerToCharacter() {
        Object coerced = Convert.coerce((int) 'a', Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }
    // Coerce from Long
    @Test
    public void canCoerceLongToByte() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceLongToShort() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceLongToInteger() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceLongToLong() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceLongToFloat() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4F, coerced);
    }
    @Test
    public void canCoerceLongToDouble() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4D, coerced);
    }
    @Test
    public void canCoerceLongToBigInteger() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceLongToBigDecimal() {
        Long b = 4L;
        Object coerced = Convert.coerce(b, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceLongToCharacter() {
        Object coerced = Convert.coerce((long) 'a', Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }
    // Coerce from Float
    @Test
    public void canCoerceFloatToByte() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceFloatToShort() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceFloatToInteger() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceFloatToLong() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceFloatToFloat() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4.14F, coerced);
    }
    @Test
    public void canCoerceFloatToDouble() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals((double) b, coerced);
    }
    @Test
    public void canCoerceFloatToBigInteger() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceFloatToBigDecimal() {
        Float b = 4.14F;
        Object coerced = Convert.coerce(b, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(4.14F, ((BigDecimal) coerced).floatValue());
    }
    @Test
    public void canCoerceFloatToCharacter() {
        Object coerced = Convert.coerce((float) 'a', Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }

    // Coerce from Double
    @Test
    public void canCoerceDoubleToByte() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceDoubleToShort() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceDoubleToInteger() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceDoubleToLong() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceDoubleToFloat() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4.14F, coerced);
    }
    @Test
    public void canCoerceDoubleToDouble() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4.14D, coerced);
    }
    @Test
    public void canCoerceDoubleToBigInteger() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceDoubleToBigDecimal() {
        Double b = 4.14D;
        Object coerced = Convert.coerce(b, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(4.14D, ((BigDecimal) coerced).doubleValue());
    }
    @Test
    public void canCoerceDoubleToCharacter() {
        Object coerced = Convert.coerce((double) 'a', Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }
    // Coerce from BigInteger
    @Test
    public void canCoerceBigIntegerToByte() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceBigIntegerToShort() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceBigIntegerToInteger() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceBigIntegerToLong() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceBigIntegerToFloat() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4F, coerced);
    }
    @Test
    public void canCoerceBigIntegerToDouble() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4D, coerced);
    }
    @Test
    public void canCoerceBigIntegerToBigInteger() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceBigIntegerToBigDecimal() {
        BigInteger b = BigInteger.valueOf(4L);
        Object coerced = Convert.coerce(b, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceBigIntegerToCharacter() {
        Object coerced = Convert.coerce(BigInteger.valueOf((long) 'a'), Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }
    // Coerce from BigDecimal
    @Test
    public void canCoerceBigDecimalToByte() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceBigDecimalToShort() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceBigDecimalToInteger() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceBigDecimalToLong() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceBigDecimalToFloat() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(b.floatValue(), coerced);
    }
    @Test
    public void canCoerceBigDecimalToDouble() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(b.doubleValue(), coerced);
    }
    @Test
    public void canCoerceBigDecimalToBigInteger() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceBigDecimalToBigDecimal() {
        BigDecimal b = BigDecimal.valueOf(4.14D);
        Object coerced = Convert.coerce(b, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(b, coerced);
    }
    @Test
    public void canCoerceBigDecimalToCharacter() {
        Object coerced = Convert.coerce(BigDecimal.valueOf((long) 'a'), Character.class);
        Assert.assertTrue(coerced instanceof Character);
        Assert.assertEquals('a', coerced);
    }
    // Coerce from boolean
    @Test
    public void canConvertFromBooleanToByte() {
        Object coerced;
        coerced = Convert.coerce(true, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 1, coerced);
        coerced = Convert.coerce(false, Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 0, coerced);
    }
    @Test
    public void canConvertFromBooleanToShort() {
        Object coerced;
        coerced = Convert.coerce(true, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 1, coerced);
        coerced = Convert.coerce(false, Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 0, coerced);
    }
    @Test
    public void canConvertFromBooleanToInteger() {
        Object coerced;
        coerced = Convert.coerce(true, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(1, coerced);
        coerced = Convert.coerce(false, Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(0, coerced);
    }
    @Test
    public void canConvertFromBooleanToLong() {
        Object coerced;
        coerced = Convert.coerce(true, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(1L, coerced);
        coerced = Convert.coerce(false, Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(0L, coerced);
    }
    @Test
    public void canConvertFromBooleanToFloat() {
        Object coerced;
        coerced = Convert.coerce(true, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(1F, coerced);
        coerced = Convert.coerce(false, Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(0F, coerced);
    }
    @Test
    public void canConvertFromBooleanToDouble() {
        Object coerced;
        coerced = Convert.coerce(true, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(1D, coerced);
        coerced = Convert.coerce(false, Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(0D, coerced);
    }
    @Test
    public void canConvertFromBooleanToBigInteger() {
        Object coerced;
        coerced = Convert.coerce(true, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(1L), coerced);
        coerced = Convert.coerce(false, BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(0), coerced);
    }
    @Test
    public void canConvertFromBooleanToBigDecimal() {
        Object coerced;
        coerced = Convert.coerce(true, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(1L), coerced);
        coerced = Convert.coerce(false, BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(0), coerced);
    }

    @Test
    public void canCoerceFromStringToByte() {
        Object coerced = Convert.coerce("4", Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 4, coerced);
    }
    @Test
    public void canCoerceFromStringToShort() {
        Object coerced = Convert.coerce("4", Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 4, coerced);
    }
    @Test
    public void canCoerceFromStringToInteger() {
        Object coerced = Convert.coerce("4", Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals(4, coerced);
    }
    @Test
    public void canCoerceFromStringToLong() {
        Object coerced = Convert.coerce("4", Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals(4L, coerced);
    }
    @Test
    public void canCoerceFromStringToFloat() {
        Object coerced;
        coerced = Convert.coerce("4", Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4F, coerced);
        coerced = Convert.coerce("4.14", Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals(4.14F, coerced);
    }
    @Test
    public void canCoerceFromStringToDouble() {
        Object coerced;
        coerced = Convert.coerce("4", Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4D, coerced);
        coerced = Convert.coerce("4.14", Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals(4.14D, coerced);
    }
    @Test
    public void canCoerceFromStringToBigInteger() {
        Object coerced;
        coerced = Convert.coerce("4", BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf(4L), coerced);
    }
    @Test
    public void canCoerceFromStringToBigDecimal() {
        Object coerced;
        coerced = Convert.coerce("4", BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(4L), coerced);
        coerced = Convert.coerce("4.14", BigDecimal.class);
        Assert.assertTrue(coerced instanceof BigDecimal);
        Assert.assertEquals(BigDecimal.valueOf(4.14D), coerced);
    }
    @Test
    public void canCoerceCharacterToByte() {
        Object coerced = Convert.coerce('a', Byte.class);
        Assert.assertTrue(coerced instanceof Byte);
        Assert.assertEquals((byte) 'a', coerced);
    }
    @Test
    public void canCoerceCharacterToShort() {
        Object coerced = Convert.coerce('a', Short.class);
        Assert.assertTrue(coerced instanceof Short);
        Assert.assertEquals((short) 'a', coerced);
    }
    @Test
    public void canCoerceCharacterToInteger() {
        Object coerced = Convert.coerce('a', Integer.class);
        Assert.assertTrue(coerced instanceof Integer);
        Assert.assertEquals((int) 'a', coerced);
    }
    @Test
    public void canCoerceCharacterToLong() {
        Object coerced = Convert.coerce('a', Long.class);
        Assert.assertTrue(coerced instanceof Long);
        Assert.assertEquals((long) 'a', coerced);
    }
    @Test
    public void canCoerceCharacterToFloat() {
        Object coerced = Convert.coerce('a', Float.class);
        Assert.assertTrue(coerced instanceof Float);
        Assert.assertEquals((float) 'a', coerced);
    }
    @Test
    public void canCoerceCharacterToDouble() {
        Object coerced = Convert.coerce('a', Double.class);
        Assert.assertTrue(coerced instanceof Double);
        Assert.assertEquals((double) 'a', coerced);
    }
    @Test
    public void canCoerceCharacterToBigInteger() {
        Object coerced = Convert.coerce('a', BigInteger.class);
        Assert.assertTrue(coerced instanceof BigInteger);
        Assert.assertEquals(BigInteger.valueOf((long) 'a'), coerced);
    }
}
