package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.node.Address;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DataPacketTest
{
    private ArrayList<UnsignedByte> payload;
    private Address destAdr;
    private DataPacket dataPacket;

    @Before
    public void setUp()
    {
        payload = new ArrayList<>();
        payload.add(UnsignedByte.of(2));
        payload.add(UnsignedByte.of(4));
        destAdr = new Address(10);

        dataPacket = new DataPacket(payload, destAdr);
    }

    @Test
    public void It_should_create_a_packet_of_type_data_with_size_12()
    {
        //assert 6th byte for Data which is 1
        assertEquals(UnsignedByte.of(1), dataPacket.getBytes().get(6));
        assertEquals(12, dataPacket.getLength());
    }


}