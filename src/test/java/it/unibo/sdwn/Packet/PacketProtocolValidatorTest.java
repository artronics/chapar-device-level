package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PacketProtocolValidatorTest
{
    private static final UnsignedByte startByte = UnsignedByte.of(Config.get().getByte("startByte"));
    private static final UnsignedByte stopByte = UnsignedByte.of(Config.get().getByte("stopByte"));
    private ArrayList<UnsignedByte> goodPacket = new ArrayList<>();
    private ArrayList<UnsignedByte> malformedPacket = new ArrayList<>();

    @Before
    public void setUp()
    {
        //lets say the packet is something like this
        //startByte lengthOfPacket someDate stopByte
        goodPacket.add(UnsignedByte.of(startByte));
        goodPacket.add(UnsignedByte.of(7)); //packet length
        goodPacket.add(UnsignedByte.of(1));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(3));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(1));//type=data
        goodPacket.add(UnsignedByte.of(stopByte));

        malformedPacket.add(UnsignedByte.of(startByte));
        malformedPacket.add(UnsignedByte.of(2)); //packet length
        malformedPacket.add(UnsignedByte.of(2));
        malformedPacket.add(UnsignedByte.of(3));

    }

    @Test
    public void Test_validation()
    {
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
        Packet.Type actType = null;
        try {
            actType = PacketProtocol.getType(goodPacket);
        }catch (MalformedPacketException e) {
            thrown = true;
        }
        assertFalse(thrown);
        assertEquals(Packet.Type.DATA, actType);
    }

}