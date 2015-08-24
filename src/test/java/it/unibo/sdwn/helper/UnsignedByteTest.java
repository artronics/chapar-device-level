package it.unibo.sdwn.helper;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnsignedByteTest
{
    @Test
    public void Test_equality()
    {
        UnsignedByte first = UnsignedByte.of(4);
        UnsignedByte second = UnsignedByte.of(4);
        UnsignedByte unEq = UnsignedByte.of(0);

        boolean equals = first.equals(second);
        boolean notEquals = first.equals(unEq);

        assertTrue(equals);
        assertFalse(notEquals);
        assertEquals(first, second);
        assertNotEquals(first, unEq);
    }

    @Test
    public void test_it_should_construct_with_short()
    {
        short shortValue = 100;
        UnsignedByte unsignedByte = UnsignedByte.of(shortValue);
        assertEquals(shortValue, unsignedByte.shortValue());
    }

    @Test
    public void test_it_should_construct_with_int()
    {
        int intValue = 100;
        UnsignedByte unsignedByte = UnsignedByte.of(intValue);
        assertEquals(intValue, unsignedByte.shortValue());
    }

    @Test
    public void Test_toString()
    {
        UnsignedByte ub = UnsignedByte.of(30);
        String ecpStr = "30";
        assertEquals(ecpStr, ub.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exp_on_overflow()
    {
        int intValue = 300;
        UnsignedByte unsignedByte = UnsignedByte.of(intValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exp_on_overflow_lower_than_negative_128()
    {
        int intValue = -129;
        UnsignedByte unsignedByte = UnsignedByte.of(intValue);
    }

    @Test
    public void It_should_convert_negative_bytes_to_positive()
    {
        UnsignedByte act = UnsignedByte.of(-128);
        UnsignedByte exp = UnsignedByte.of(128);
        assertEquals(exp, act);

        act = UnsignedByte.of(0);
        exp = UnsignedByte.of(0);
        assertEquals(exp, act);

        act = UnsignedByte.of(-1);
        exp = UnsignedByte.of(255);
        assertEquals(exp, act);

        act = UnsignedByte.of(-56);
        exp = UnsignedByte.of(200);
        assertEquals(exp, act);
    }

    @Test
    public void It_should_converet_a_byte_array_to_UnsignedByte_array()
    {
        byte[] b = new byte[15];
        b[0] = 0;
        b[1] = 1;
        b[2] = 3;
        int length = 3;
        UnsignedByte[] act = UnsignedByte.toUnsignedByteArray(b, length);

        assertEquals(3, act.length);
        assertEquals(b[0], act[0].byteValue());
        assertEquals(b[1], act[1].byteValue());
        assertEquals(b[2], act[2].byteValue());

    }

}