package artronics.chapar.node;

import artronics.chapar.address.Address;
import artronics.chapar.address.BaseAddress;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseAddressTest
{
    Address add1;
    Address add2;
    Address notEqAdd;

    @Before
    public void setUp() throws Exception
    {
        add1 = new BaseAddress(10);
        add2 = new BaseAddress(10);
        notEqAdd = new BaseAddress(20);
    }

    @Test
    public void Test_equality()
    {
        assertNotEquals(add1, null);
        //reflexive
        assertEquals(add1, add1);
        //symmetry
        assertTrue(add1.equals(add2));// AND
        assertTrue(add2.equals(add1));

        assertNotEquals(add1, notEqAdd);
    }

    @Test
    public void Test_hash_code()
    {
        assertEquals(add1.hashCode(), add2.hashCode());
        assertNotEquals(add1.hashCode(), notEqAdd.hashCode());
    }
}