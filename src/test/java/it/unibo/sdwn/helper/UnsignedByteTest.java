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

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exp_on_overflow()
    {
        int intValue = 300;
        UnsignedByte unsignedByte = UnsignedByte.of(intValue);
    }

}