package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PcketSerializerTest
{
    private ArrayList<UnsignedByte> pck = new ArrayList<>();
    private SdwnPacket packet;
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

        packet = new SdwnPacket(pck);
        csv = packet.toCsv();

        expCsv = "DATA; 1; ";
    }

    @Test
    public void It_should_create_csv_string()
    {
        assertEquals(expCsv, csv);
    }
}