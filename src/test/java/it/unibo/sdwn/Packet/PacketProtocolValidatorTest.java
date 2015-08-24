package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

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
        goodPacket.add(UnsignedByte.of(4)); //packet length
        goodPacket.add(UnsignedByte.of(1));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(3));
        goodPacket.add(UnsignedByte.of(stopByte));

        malformedPacket.add(UnsignedByte.of(startByte));
        malformedPacket.add(UnsignedByte.of(2)); //packet length
        malformedPacket.add(UnsignedByte.of(2));
        malformedPacket.add(UnsignedByte.of(3));

    }

    @Test
    public void Test_validation()
    {
        assertTrue(PacketProtocol.validate(goodPacket));
    }

}