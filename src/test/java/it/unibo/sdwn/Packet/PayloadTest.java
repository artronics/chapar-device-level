package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PayloadTest
{
    private ArrayList<UnsignedByte> arrayList = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {
        arrayList.add(new UnsignedByte(0));
        arrayList.add(new UnsignedByte(1));
        arrayList.add(new UnsignedByte(2));
        arrayList.add(new UnsignedByte(3));
    }

    @Test
    public void It_should_construct_with_given_length()
    {
        Payload payload = new Payload(4);
        payload.initWithDummyValues();

        assertEquals(arrayList, payload.getData());
    }

    @Test
    public void It_should_be_init_with_arrayList()
    {
        ArrayList<UnsignedByte> act = new ArrayList<>();
        act.add(new UnsignedByte(4));
        act.add(new UnsignedByte(3));
        act.add(new UnsignedByte(8));

        Payload payload = new Payload(act.size());
        payload.init(act);
        assertEquals(act, payload.getData());
    }
}