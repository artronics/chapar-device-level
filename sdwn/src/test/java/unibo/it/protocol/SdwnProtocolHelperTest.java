package unibo.it.protocol;

import artronics.chapar.packet.Packet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SdwnProtocolHelperTest
{
    private ArrayList<Integer> goodPacket;
    private ArrayList<Integer> malformedPacket;

    @Before
    public void setUp()
    {
        goodPacket = FakePacketFactory.buildGoodPacket();
    }

    @Test
    public void Test_validation()
    {
        goodPacket = FakePacketFactory.buildGoodPacket();
        boolean isvalid = SdwnProtocolHelper.validate(goodPacket);
        assertTrue(isvalid);
    }

    @Test
    public void Test_getType()
    {
        Packet.Type actType = SdwnProtocolHelper.getType(goodPacket);
        assertEquals(Packet.Type.DATA, actType);

        //another time for Rule request
        goodPacket.clear();
        goodPacket = FakePacketFactory.buildGoodPacket(Packet.Type.RULE_REQUEST);
        actType = SdwnProtocolHelper.getType(goodPacket);
        assertEquals(Packet.Type.RULE_REQUEST, actType);
    }

    @Test
    public void If_source_of_packet_is_sink_direction_is_out()
    {
        goodPacket.set(ByteMeaning.SOURCE_H.getValue(), 0); //address of sink
        goodPacket.set(ByteMeaning.SOURCE_L.getValue(), 0);
        Packet.Direction packetDir = SdwnProtocolHelper.chooseDirection(goodPacket);

        Packet.Direction outDir = Packet.Direction.OUT;
        assertThat(packetDir, equalTo(outDir));

    }

    @Test
    public void If_source_of_packet_is_ohter_than_sink_direction_is_in()
    {
        goodPacket.set(ByteMeaning.SOURCE_H.getValue(), 7);
        goodPacket.set(ByteMeaning.SOURCE_L.getValue(), 0);
        Packet.Direction packetDir = SdwnProtocolHelper.chooseDirection(goodPacket);

        Packet.Direction inDir = Packet.Direction.IN;
        assertThat(packetDir, equalTo(inDir));
    }

    @Test
    public void Test_getIntAddress_it_gets_two_int_and_returns_int()
    {
        int l = 10;
        int h = 0;
        int actMixed = SdwnProtocolHelper.getIntAddress(l, h);
        int exp = 10;
        assertEquals(exp, actMixed);

        l = 0;
        exp = 0;
        actMixed = SdwnProtocolHelper.getIntAddress(l, h);
        assertEquals(exp, actMixed);

        l = 4;
        h = 1;
        exp = 260; //256 +4
        actMixed = SdwnProtocolHelper.getIntAddress(l, h);
        assertEquals(exp, actMixed);
    }

}