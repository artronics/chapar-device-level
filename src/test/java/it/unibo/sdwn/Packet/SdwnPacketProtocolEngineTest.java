package it.unibo.sdwn.packet;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;
import it.unibo.sdwn.packet.protocol.sdwn.FakeSdwnPacketFactory;
import it.unibo.sdwn.packet.protocol.sdwn.SdwnPacketProtocolEngine;
import it.unibo.sdwn.trasport.exceptions.MalformedPacketException;
import it.unibo.sdwn.trasport.exceptions.PacketNotReadyException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SdwnPacketProtocolEngineTest
{
    private static final UnsignedByte startByte = UnsignedByte.of(Config.get().getByte("startByte"));
    private static final UnsignedByte stopByte = UnsignedByte.of(Config.get().getByte("stopByte"));
    private ArrayList<UnsignedByte> goodPacket = new ArrayList<>();
    private ArrayList<UnsignedByte> malformedPacket = new ArrayList<>();
    private SdwnPacketProtocolEngine packetProtocol;

    @Before
    public void setUp()
    {
        packetProtocol = new SdwnPacketProtocolEngine();
        //lets say the packet is something like this
        //startByte lengthOfPacket someDate stopByte
        goodPacket = FakeSdwnPacketFactory.buildGoodPacket();

        malformedPacket.add(UnsignedByte.of(startByte));
        malformedPacket.add(UnsignedByte.of(2)); //packet length
        malformedPacket.add(UnsignedByte.of(2));
        malformedPacket.add(UnsignedByte.of(3));
    }

    private void setBytes()
    {
        packetProtocol.addByte(UnsignedByte.of(startByte));
        packetProtocol.addByte(UnsignedByte.of(7));//length
        packetProtocol.addByte(UnsignedByte.of(1));
        packetProtocol.addByte(UnsignedByte.of(2));
        packetProtocol.addByte(UnsignedByte.of(3));
        packetProtocol.addByte(UnsignedByte.of(2));
        packetProtocol.addByte(UnsignedByte.of(2));
        packetProtocol.addByte(UnsignedByte.of(1));//Data
        packetProtocol.addByte(UnsignedByte.of(stopByte));
    }

    @Test
    public void It_should_construct_packet()
    {
        setBytes();
        ArrayList<UnsignedByte> actualPacket = packetProtocol.getReceivedBytes();

        assertEquals(goodPacket, actualPacket);
    }

    @Test
    public void If_packet_is_ready_and_we_send_unapproprited_bytes_it_should_give_last_packet()
    {
        //first send appropriate bytes
        setBytes();
        //then send unappropriated bytes
        packetProtocol.addByte(UnsignedByte.of(7));
        packetProtocol.addByte(UnsignedByte.of(4));

        ArrayList<UnsignedByte> actualPacket = packetProtocol.getReceivedBytes();

        assertEquals(goodPacket, actualPacket);
    }

    @Test
    public void It_should_construct_a_new_packet_after_finishig_previous_one()
    {
        //now lets send two sequence of good packets
        It_should_construct_packet();
        It_should_construct_packet();

        //now lets mixed above methods
        If_packet_is_ready_and_we_send_unapproprited_bytes_it_should_give_last_packet();
        It_should_ignore_non_sense_bytes_BEFORE_getting_start_byte();
    }



    @Test
    public void It_should_ignore_non_sense_bytes_BEFORE_getting_start_byte()
    {
        //lets addByte some in appropriate bytes
        packetProtocol.addByte(UnsignedByte.of(7));
        packetProtocol.addByte(UnsignedByte.of(4));
        //then appropriate bytes
        setBytes();
        ArrayList<UnsignedByte> actualPacket = packetProtocol.getReceivedBytes();
        assertEquals(goodPacket, actualPacket);
    }

    @Test
    public void It_should_be_clear_when_instantiated()
    {
        assertFalse(packetProtocol.isPacketReady());
    }
    @Test
    public void It_should_be_clear_after_geting_the_packet_and_invoking_clear()
    {
            setBytes();
            ArrayList<UnsignedByte> getThePacket = packetProtocol.getReceivedBytes();
        packetProtocol.clear();
        //then
        It_should_be_clear_when_instantiated();
    }

    @Test
    public void It_should_validate_received_bytes()
    {
        //lets create a array of good packet
        ArrayList<UnsignedByte> good = new ArrayList<>(4);
        good.add(startByte);
        good.add(UnsignedByte.of(2));
        good.add(UnsignedByte.of(2));
        good.add(stopByte);
        boolean isvalid = false;
        isvalid = packetProtocol.isValid(good);
        assertTrue(isvalid);

        //Assert false situation
        //lets create a array of bad packet
        ArrayList<UnsignedByte> bad = new ArrayList<>(4);
        bad.add(startByte);
        bad.add(UnsignedByte.of(2));
        bad.add(UnsignedByte.of(2));
        bad.add(UnsignedByte.of(2));//add extra byte
        bad.add(stopByte);
        isvalid = true;
        isvalid = packetProtocol.isValid(bad);
        assertFalse(isvalid);
    }
}