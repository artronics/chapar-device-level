package it.unibo.sdwn.Packet;

import it.unibo.sdwn.Packet.sdwn.FakeSdwnPacketFactory;
import it.unibo.sdwn.Packet.sdwn.PacketProtocolHelper;
import it.unibo.sdwn.Packet.sdwn.SdwnPacketType;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class SdwnPacketProtocolHelperValidatorTest
{
    private ArrayList<UnsignedByte> goodPacket ;
    private ArrayList<UnsignedByte> malformedPacket ;

    @Before
    public void setUp()
    {
    }

    @Test
    public void Test_validation()
    {
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket();
        boolean thrown = false;
        try {
            PacketProtocolHelper.validate(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void Test_getType()
    {
        boolean thrown = false;
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket();

        SdwnPacketType.Type actType = null;
        try {
            actType = PacketProtocolHelper.getType(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(SdwnPacketType.Type.DATA, actType);

        //another time for Rule request
        goodPacket.clear();
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket(SdwnPacketType.Type.RULE_REQUEST);
        try {
            actType = PacketProtocolHelper.getType(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(SdwnPacketType.Type.RULE_REQUEST, actType);
    }

}