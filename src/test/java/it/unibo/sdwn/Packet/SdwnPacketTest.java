package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SdwnPacketTest
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

        packet.set(6, new UnsignedByte(3));
        SdwnPacket sdwnPacket1 = new SdwnPacket(packet);
        assertTrue(sdwnPacket1.is(SdwnPacket.PacketType.REPORT));

        packet.set(6, new UnsignedByte(2));
        SdwnPacket sdwnPacket2 = new SdwnPacket(packet);
        assertFalse(sdwnPacket2.is(SdwnPacket.PacketType.REPORT));
    }

    @Test
    public void It_should_consider_packet_type_as_OPEN_PATH_if_Type_value_is_greater_than_6()
    {
        packet.set(6, new UnsignedByte(7));
        SdwnPacket sdwnPacket = new SdwnPacket(packet);
        assertTrue(sdwnPacket.is(SdwnPacket.PacketType.OPEN_PATH));
    }

}