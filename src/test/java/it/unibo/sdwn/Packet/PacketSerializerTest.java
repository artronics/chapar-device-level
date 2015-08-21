package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.node.Address;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PacketSerializerTest
{
    private ArrayList<UnsignedByte> pck = new ArrayList<>();
    private Address add = new Address(10);
    private SdwnPacket dataPacket;
    private String csv = "";
    private String expCsv;

    @Before
    public void setUp()
    {
        pck.add(new UnsignedByte(5));
        pck.add(new UnsignedByte(5));
        pck.add(new UnsignedByte(5));
        pck.add(new UnsignedByte(5));
        pck.add(new UnsignedByte(5));
        pck.add(new UnsignedByte(5));
        pck.add(new UnsignedByte(1));//Type:6
        pck.add(new UnsignedByte(5));

        dataPacket = new DataPacket(pck, add);
        csv = dataPacket.toCsv();

        expCsv = "DATA; 2; ";
    }

    @Test
    public void It_should_create_csv_string()
    {
        assertEquals(expCsv, csv);
    }
}