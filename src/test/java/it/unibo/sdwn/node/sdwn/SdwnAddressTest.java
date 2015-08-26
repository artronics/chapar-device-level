package it.unibo.sdwn.node.sdwn;

import it.unibo.sdwn.node.Address;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SdwnAddressTest
{
    Address add1;
    Address add2;
    Address notEqAdd;

    @Before
    public void setUp() throws Exception
    {
        add1 = new SdwnAddress(10);
        add2 = new SdwnAddress(10);
        notEqAdd = new SdwnAddress(20);
    }

    @Test
    public void Test_equality()
    {
        assertEquals(add1, add2);
        assertNotEquals(add1, notEqAdd);
    }

    @Test
    public void Test_hash_code()
    {
        assertEquals(add1.hashCode(), add2.hashCode());
        assertNotEquals(add1.hashCode(), notEqAdd.hashCode());
    }
}