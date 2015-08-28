package artronics.chapar.packet;

import artronics.chapar.packet.protocol.sdwn.FakeSdwnPacketFactory;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocolEngine;
import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SdwnPacketProtocolHelperValidatorTest
{
    private ArrayList<UnsignedByte> goodPacket;
    private ArrayList<UnsignedByte> malformedPacket;

    @Before
    public void setUp()
    {
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket();
    }

    @Test
    public void Test_validation()
    {
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket();
        boolean isvalid = SdwnPacketProtocolEngine.PacketProtocolHelper.validate(goodPacket);
        assertTrue(isvalid);
    }

    @Test
    public void Test_getType()
    {
        SdwnPacketType actType = SdwnPacketProtocolEngine.PacketProtocolHelper.getType(goodPacket);
        assertEquals(SdwnPacketType.DATA, actType);

        //another time for Rule request
        goodPacket.clear();
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket(SdwnPacketType.RULE_REQUEST);
        actType = SdwnPacketProtocolEngine.PacketProtocolHelper.getType(goodPacket);
        assertEquals(SdwnPacketType.RULE_REQUEST, actType);
    }

}