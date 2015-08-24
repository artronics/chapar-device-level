package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PacketProtocolValidatorTest
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
        goodPacket = FakePacketFactory.buildGoodPacket();
        boolean thrown = false;
        try {
            PacketProtocol.validate(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @Test
    public void Test_getType()
    {
        boolean thrown = false;
        goodPacket = FakePacketFactory.buildGoodPacket();

        Packet.Type actType = null;
        try {
            actType = PacketProtocol.getType(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(Packet.Type.DATA, actType);

        //another time for Rule request
        goodPacket.clear();
        goodPacket = FakePacketFactory.buildGoodPacket(Packet.Type.RULE_REQUEST);
        try {
            actType = PacketProtocol.getType(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(Packet.Type.RULE_REQUEST, actType);
    }

}