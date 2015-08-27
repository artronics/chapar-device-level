package it.unibo.sdwn.Packet;

import it.unibo.sdwn.Packet.protocol.sdwn.FakeSdwnPacketFactory;
import it.unibo.sdwn.Packet.protocol.sdwn.PacketProtocolHelper;
import it.unibo.sdwn.Packet.protocol.sdwn.SdwnPacketType;
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

        SdwnPacketType actType = null;
        try {
            actType = PacketProtocolHelper.getType(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(SdwnPacketType.DATA, actType);

        //another time for Rule request
        goodPacket.clear();
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket(SdwnPacketType.RULE_REQUEST);
        try {
            actType = PacketProtocolHelper.getType(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(SdwnPacketType.RULE_REQUEST, actType);
    }

}