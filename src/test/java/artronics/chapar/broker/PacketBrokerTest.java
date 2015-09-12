package artronics.chapar.broker;

import artronics.chapar.FakePacketFactory;
import artronics.chapar.packet.Packet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:chapar_DI.xml")
public class PacketBrokerTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    PacketBroker packetBroker;

//    @Autowired
//    ConnectionService mockSerialPort;

    @Autowired
    MessagesIn messagesIn;

    @Autowired
    PacketsIn packetsIn;

    List<Integer> packet = FakePacketFactory.buildGoodPacket();

    @Test
    public void It_should_give_the_correct_type()
    {
        messagesIn.put(packet);
        while (!packetsIn.isEmpty()) {
            Packet receivedPacket = packetsIn.take();
            assertThat(receivedPacket.getType(), equalTo("UNK"));
        }
    }

    @Test
    public void It_should_create_Packets_from_buffer() throws InterruptedException
    {
        //put all buffers to messageIn
        List<List<Integer>> buffers = new FakeConnectionBuffer().getAllBuffres();
        for (List<Integer> buffer : buffers) {
            messagesIn.put(buffer);
        }

        //lets see if everything is working
        List<Packet> packets = new ArrayList<>();
        while (!packetsIn.isEmpty()) {
            packets.add(packetsIn.take());
        }

        //we just need to know the number of packets
        assertThat(packets.size(), equalTo(10));
    }
}