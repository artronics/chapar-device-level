package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class PacketTest
{
    private ArrayList<UnsignedByte> packet = new ArrayList<>();

    @Before
    public void setUp()
    {
        packet.add(new UnsignedByte(5));
        packet.add(new UnsignedByte(5));
        packet.add(new UnsignedByte(5));
        packet.add(new UnsignedByte(5));
        packet.add(new UnsignedByte(5));
        packet.add(new UnsignedByte(5));
        packet.add(new UnsignedByte(1));//Type:6
        packet.add(new UnsignedByte(5));

    }

    @Test
    public void It_should_tell_us_if_packet_has_specific_type()
    {

        SdwnPacket sdwnPacket = new SdwnPacket(packet);
        assertTrue(sdwnPacket.is(SdwnPacket.PacketType.DATA));
    }

}