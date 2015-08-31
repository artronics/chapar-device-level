package unibo.it.protocol;

import artronics.chapar.packet.Packet;
import artronics.chapar.protocol.PacketProtocol;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.*;
import static org.junit.Assert.assertThat;

public class SdwnPacketProtocolTest
{
    private static final FakePacketFactory fakeFactory = new FakePacketFactory();
    private static final int startByte = FakePacketFactory.startByte;
    private static final int stopByte = FakePacketFactory.stopByte;

    PacketProtocol sdwnProtocol;
    ArrayList<Integer> goodPacket = new ArrayList();

    @Before
    public void setUp() throws Exception
    {
        sdwnProtocol = new SdwnPacketProtocol();
        goodPacket = FakePacketFactory.buildGoodPacket(Packet.Type.DATA);


    }

    private void setBytes()
    {
        sdwnProtocol.addByte(startByte);
        sdwnProtocol.addByte(7);//length
        sdwnProtocol.addByte(1);
        sdwnProtocol.addByte(2);
        sdwnProtocol.addByte(3);
        sdwnProtocol.addByte(2);
        sdwnProtocol.addByte(2);
        sdwnProtocol.addByte(0);//Data
        sdwnProtocol.addByte(stopByte);
    }

    @Test
    public void It_should_construct_packet()
    {
        setBytes();
        ArrayList<Integer> actualPacket = sdwnProtocol.getReceivedBytes();

        assertThat(actualPacket, equalTo(goodPacket));
    }

    @Test
    public void If_packet_is_ready_and_we_send_unapproprited_bytes_it_should_give_last_packet()
    {
        //first send appropriate bytes
        setBytes();
        //then send unappropriated bytes
        sdwnProtocol.addByte(7);
        sdwnProtocol.addByte(4);

        ArrayList<Integer> actualPacket = sdwnProtocol.getReceivedBytes();

        assertThat(actualPacket, equalTo(goodPacket));
    }

    @Test
    public void It_should_ignore_non_sense_bytes_BEFORE_getting_start_byte()
    {
        //lets addByte some in appropriate bytes
        sdwnProtocol.addByte(7);
        sdwnProtocol.addByte(4);
        //then appropriate bytes
        setBytes();
        ArrayList<Integer> actualPacket = sdwnProtocol.getReceivedBytes();

        assertThat(actualPacket, equalTo(goodPacket));
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
    public void It_should_be_clear_when_instantiated()
    {
        assertFalse(sdwnProtocol.isPacketReady());
    }

    @Test
    public void It_should_be_clear_after_geting_the_packet_and_invoking_clear()
    {
        setBytes();
        ArrayList<Integer> getThePacket = sdwnProtocol.getReceivedBytes();
        sdwnProtocol.clear();
        //then
        It_should_be_clear_when_instantiated();
    }

}