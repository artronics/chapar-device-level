package it.unibo.sdwn.helper;

import junit.framework.TestCase;
import org.junit.Test;

public class UnsignedByteTest extends TestCase
{
    public void test_it_should_construct_with_short(){
        short shortValue = 100;
        UnsignedByte unsignedByte = new UnsignedByte(shortValue);
        assertEquals(shortValue, unsignedByte.shortValue());
    }
    public void test_it_should_construct_with_int(){
        int intValue = 100;
        UnsignedByte unsignedByte = new UnsignedByte(intValue);
        assertEquals(intValue, unsignedByte.shortValue());
    }

    @Test(expected= IllegalArgumentException.class)
    public void should_throw_exp_on_overflow(){
        int intValue = 300;
            UnsignedByte unsignedByte = new UnsignedByte(intValue);
    }

}