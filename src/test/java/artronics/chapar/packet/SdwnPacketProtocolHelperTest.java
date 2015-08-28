package artronics.chapar.packet;

import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.protocol.sdwn.FakeSdwnPacketFactory;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketProtocolHelper;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SdwnPacketProtocolHelperTest
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
        boolean isvalid = SdwnPacketProtocolHelper.validate(goodPacket);
        assertTrue(isvalid);
    }

    @Test
    public void Test_getType()
    {
        SdwnPacketType actType = SdwnPacketProtocolHelper.getType(goodPacket);
        assertEquals(SdwnPacketType.DATA, actType);

        //another time for Rule request
        goodPacket.clear();
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket(SdwnPacketType.RULE_REQUEST);
        actType = SdwnPacketProtocolHelper.getType(goodPacket);
        assertEquals(SdwnPacketType.RULE_REQUEST, actType);
    }

    @Test
    public void Test_getIntAddress_it_gets_two_UnsignedByte_and_returns_int()
    {
        UnsignedByte l = UnsignedByte.of(10);
        UnsignedByte h = UnsignedByte.of(0);
        int actMixed = SdwnPacketProtocolHelper.getIntAddress(l, h);
        int exp = 10;
        assertEquals(exp, actMixed);

        l = UnsignedByte.of(0);
        exp = 0;
        actMixed = SdwnPacketProtocolHelper.getIntAddress(l, h);
        assertEquals(exp, actMixed);

        l = UnsignedByte.of(4);
        h = UnsignedByte.of(1);
        exp = 260; //256 +4
        actMixed = SdwnPacketProtocolHelper.getIntAddress(l, h);
        assertEquals(exp, actMixed);
    }

}