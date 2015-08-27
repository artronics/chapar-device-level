package it.unibo.sdwn.Packet;

import it.unibo.sdwn.Packet.protocol.sdwn.SdwnPacketProtocolEngine;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.helper.UnsignedByte;
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
        goodPacket.add(UnsignedByte.of(4)); //packet length
        goodPacket.add(UnsignedByte.of(1));
        goodPacket.add(UnsignedByte.of(2));
        goodPacket.add(UnsignedByte.of(3));

        malformedPacket.add(UnsignedByte.of(startByte));
        malformedPacket.add(UnsignedByte.of(2)); //packet length
        malformedPacket.add(UnsignedByte.of(2));
        malformedPacket.add(UnsignedByte.of(3));
    }

    private void setBytes() throws MalformedPacketException, PacketNotReadyException
    {
        packetProtocol.addByte(UnsignedByte.of(startByte));
        packetProtocol.addByte(UnsignedByte.of(4));
        packetProtocol.addByte(UnsignedByte.of(1));
        packetProtocol.addByte(UnsignedByte.of(2));
        packetProtocol.addByte(UnsignedByte.of(3));
        packetProtocol.addByte(UnsignedByte.of(stopByte));

    }

    @Test
    public void It_should_construct_packet() throws PacketNotReadyException, MalformedPacketException
    {
        setBytes();
        ArrayList<UnsignedByte> actualPacket = packetProtocol.getReceivedBytes();

        assertEquals(goodPacket, actualPacket);
    }

    @Test
    public void It_should_construct_a_new_packet_after_finishig_previous_one() throws PacketNotReadyException,
            MalformedPacketException
    {
        //now lets send two sequence of good packets
        It_should_construct_packet();
        It_should_construct_packet();

        //now lets mixed above methods
        If_packet_is_ready_and_we_send_unapproprited_bytes_it_should_give_last_packet();
        It_should_ignore_non_sense_bytes_BEFORE_getting_start_byte();
    }


    @Test
    public void If_packet_is_ready_and_we_send_unapproprited_bytes_it_should_give_last_packet() throws
            PacketNotReadyException, MalformedPacketException
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
    public void It_should_ignore_non_sense_bytes_BEFORE_getting_start_byte() throws MalformedPacketException,
            PacketNotReadyException
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
    public void It_should_be_clear_after_geting_the_packet()
    {
        try {
            setBytes();
            ArrayList<UnsignedByte> getThePacket = packetProtocol.getReceivedBytes();
        }catch (PacketNotReadyException packetNotReady) {
        }catch (MalformedPacketException malformedPacket1) {
        }
        //then
        It_should_be_clear_when_instantiated();
    }

    @Test
    public void It_should_be_clear_when_instantiated()
    {
        //if packet is not ready it should throw packetNotReady
        boolean thrown = false;
        try {
            packetProtocol.getReceivedBytes();
        }catch (MalformedPacketException e) {
            thrown = true;
            e.printStackTrace();
        }

        assertTrue(thrown);
        //isReady also should return false
        assertFalse(packetProtocol.isReady());

    }

    @Test(expected = MalformedPacketException.class)
    public void It_should_throw_exp_for_malformed_packet() throws PacketNotReadyException, MalformedPacketException
    {
        packetProtocol.addByte(malformedPacket.get(0));
        packetProtocol.addByte(malformedPacket.get(1));
        packetProtocol.addByte(malformedPacket.get(2));
        packetProtocol.addByte(malformedPacket.get(3));
        ArrayList<UnsignedByte> actualPacket = packetProtocol.getReceivedBytes();
    }

    @Test
    public void It_shoud_be_clear_after_throwing_exceptions()
    {
        try {

            packetProtocol.getReceivedBytes();
        }catch (MalformedPacketException e) {
            assertFalse(packetProtocol.isReady());
            e.printStackTrace();
        }
        try {
            packetProtocol.addByte(malformedPacket.get(0));
            packetProtocol.addByte(malformedPacket.get(1));
            packetProtocol.addByte(malformedPacket.get(2));
            packetProtocol.addByte(malformedPacket.get(3));
            ArrayList<UnsignedByte> actualPacket = packetProtocol.getReceivedBytes();
        }catch (MalformedPacketException malformedPacket1) {
            assertFalse(packetProtocol.isReady());
        }
    }

    @Test
    public void It_should_validate_a_receive_bytes() throws MalformedPacketException
    {
        //lets create a array of good packet
        ArrayList<UnsignedByte> good = new ArrayList<>(4);
        good.add(startByte);
        good.add(UnsignedByte.of(2));
        good.add(UnsignedByte.of(2));
        good.add(stopByte);
        boolean isvalid = false;
        isvalid = packetProtocol.validateReceivedBytes(good);
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
        isvalid = packetProtocol.validateReceivedBytes(bad);
        assertFalse(isvalid);
    }
}