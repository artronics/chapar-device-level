package it.unibo.sdwn.helper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnsignedByteTest
{
    @Test
    public void Test_equality()
    {
        UnsignedByte first = new UnsignedByte(4);
        UnsignedByte second = new UnsignedByte(4);

        assertTrue(first.equals(second));
        assertEquals(first, second);
    }

    @Test
    public void test_it_should_construct_with_short()
    {
        short shortValue = 100;
        UnsignedByte unsignedByte = new UnsignedByte(shortValue);
        assertEquals(shortValue, unsignedByte.shortValue());
    }

    @Test
    public void test_it_should_construct_with_int()
    {
        int intValue = 100;
        UnsignedByte unsignedByte = new UnsignedByte(intValue);
        assertEquals(intValue, unsignedByte.shortValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exp_on_overflow()
    {
        int intValue = 300;
        UnsignedByte unsignedByte = new UnsignedByte(intValue);
    }

}