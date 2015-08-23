/*package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SdwnPacketTest
{
    private ArrayList<UnsignedByte> packet = new ArrayList<>();

    @Before
    public void setUp()
    {
        packet.add(UnsignedByte.of(5));
        packet.add(UnsignedByte.of(5));
        packet.add(UnsignedByte.of(5));
        packet.add(UnsignedByte.of(5));
        packet.add(UnsignedByte.of(5));
        packet.add(UnsignedByte.of(5));
        packet.add(UnsignedByte.of(1));//Type:6
        packet.add(UnsignedByte.of(5));
    }

    @Test(expected = NullPointerException.class)
    public void Throw_exp_if_data_is_null()
    {
        SdwnPacket sdwnPacket = new DataPacket();
    }
    @Test
    public void It_should_tell_us_if_packet_has_specific_type()
    {
        SdwnPacket sdwnPacket = new SdwnPacket(packet);
        assertTrue(sdwnPacket.is(SdwnPacket.Type.DATA));

        packet.set(6, UnsignedByte.of(3));
        SdwnPacket sdwnPacket1 = new SdwnPacket(packet);
        assertTrue(sdwnPacket1.is(SdwnPacket.Type.REPORT));

        packet.set(6, UnsignedByte.of(2));
        SdwnPacket sdwnPacket2 = new SdwnPacket(packet);
        assertFalse(sdwnPacket2.is(SdwnPacket.Type.REPORT));
    }

    @Test
    //see class comments and todoo
    public void It_should_consider_packet_type_as_OPEN_PATH_if_Type_value_is_greater_than_6()
    {
        packet.set(6, UnsignedByte.of(7));
        SdwnPacket sdwnPacket = new SdwnPacket(packet);
        assertTrue(sdwnPacket.is(SdwnPacket.Type.OPEN_PATH));
    }

    //TODO don't forget to add other constructors
    @Test()
    public void It_sould_increment_receivedCounter_after_each_construction()
    {
        SdwnPacket sdwnPacket = new SdwnPacket(packet);
        SdwnPacket sdwnPacket1 = new SdwnPacket(packet);
        SdwnPacket sdwnPacket2 = new SdwnPacket(packet);

        //As it turns out junit run other threads on different test
        //This is a reason why it is 5 instead of 3 !
        assertEquals(5, SdwnPacket.getPacketCounter());
    }
}
*/