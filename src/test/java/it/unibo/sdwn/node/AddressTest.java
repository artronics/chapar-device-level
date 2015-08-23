package it.unibo.sdwn.node;

import it.unibo.sdwn.helper.UnsignedByte;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddressTest
{
    @Test
    public void It_should_split_an_int_value_to_two_bytes(){
        int intAddress = 256;
        UnsignedByte firstAddrByte = UnsignedByte.of(1);
        UnsignedByte secondAddrByte = UnsignedByte.of(0);
        Address address = new Address();
        UnsignedByte[] actual = address.getAddress(intAddress);
        assertEquals(firstAddrByte,actual[0]);
        assertEquals(secondAddrByte,actual[1]);
    }
    @Test
    public void It_should_split_an_int_value_to_two_bytes_in_constructor(){
        int intAddress = 256;
        UnsignedByte firstAddrByte = UnsignedByte.of(1);
        UnsignedByte secondAddrByte = UnsignedByte.of(0);
        Address address = new Address(intAddress);
        UnsignedByte[] actual = address.getAddress(intAddress);
        assertEquals(firstAddrByte,actual[0]);
        assertEquals(secondAddrByte,actual[1]);
    }
    @Test
    public void test_toString_compareTo_and_equal(){
        int intAddress = 256;
        Address firstAddress = new Address(intAddress);
        Address secondAddress = new Address(intAddress);
        Address thirdAddress = new Address(intAddress+1);
        assertTrue(firstAddress.equals(secondAddress));
        assertEquals(firstAddress.toString(),"1.0");
        assertEquals(firstAddress.compareTo(thirdAddress),-1);
    }
}