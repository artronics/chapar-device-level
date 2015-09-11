package artronics.chapar.broker;

import artronics.chapar.FakePacketFactory;
import artronics.chapar.packet.Packet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:chapar_DI.xml")
public class MessageToPacketConvertorImplTest
{
    MessageToPacketConvertorI convertor;

    FakeConnectionBuffer fakeBuffers = new FakeConnectionBuffer();
    List<Integer> goodPacket;
    List<Packet> generatedPackets;

    List<Integer> firstOne = fakeBuffers.getFirstOne();
    List<Integer> secondOne = fakeBuffers.getSecondOne();
    List<Integer> twoPackets = fakeBuffers.getTwoPackets();
    List<Integer> withGibberish = fakeBuffers.getWithGibberish();
    List<Integer> withStartStop = fakeBuffers.getWithStartStop();
    List<Integer> withStartStop_anotherPacket = fakeBuffers.getWithStartStop_anotherPacket();
    List<Integer> firstHalf = fakeBuffers.getFirstHalf();
    List<Integer> secondHalf = fakeBuffers.getSecondHalf();
    List<Integer> fullHalf = fakeBuffers.getFullHalf();
    List<Integer> mixed = fakeBuffers.getMixed();

    @Before
    public void setUp() throws Exception
    {
        goodPacket = FakePacketFactory.buildGoodPacket();
        ApplicationContext context = new ClassPathXmlApplicationContext("chapar_DI.xml");
        convertor = context.getBean(MessageToPacketConvertorI.class);
    }

    private static List removeStartStop(List<Integer> packet)
    {
        List exp = packet.subList(1, packet.size() - 1);
        return exp;
    }

    @Test
    public void If_we_pass_a_full_packet_it_should_return_that_as_it_is()
    {
        generatedPackets = convertor.generatePackets(goodPacket);

        //first sice we send one packet size must be 1
        assertThat(generatedPackets.size(), equalTo(1));

        //remove start and stop bytes
        List exp = goodPacket.subList(1, goodPacket.size() - 1);
        assertThat(generatedPackets.get(0).getBytes(), equalTo(exp));
    }

    @Test
    public void As_long_as_queue_has_data_it_should_create_packets()
    {
        generatedPackets = convertor.generatePackets(twoPackets);
        //first test the size
        assertThat(generatedPackets.size(), equalTo(2));

        assertThat(generatedPackets.get(0).getBytes(), equalTo(firstOne));
        assertThat(generatedPackets.get(1).getBytes(), equalTo(secondOne));
    }

    @Test
    public void It_sould_ignore_un_approprite_bytes_before_start_and_after_stop()
    {
        generatedPackets = convertor.generatePackets(withGibberish);
        //first test the size
        assertThat(generatedPackets.size(), equalTo(2));

        assertThat(generatedPackets.get(0).getBytes(), equalTo(firstOne));
        assertThat(generatedPackets.get(1).getBytes(), equalTo(secondOne));

    }

    @Test
    public void If_data_consists_of_start_and_stop_bytes_it_should_not_fuck_up()
    {
        generatedPackets = convertor.generatePackets(withStartStop);
        //first test the size
        List exp = removeStartStop(withStartStop);
        assertThat(generatedPackets.size(), equalTo(1));
        assertThat(generatedPackets.get(0).getBytes(), equalTo(exp));

        //now lets add another packet to end of unibo
        generatedPackets = convertor.generatePackets(withStartStop_anotherPacket);
        assertThat(generatedPackets.size(), equalTo(2));

        assertThat(generatedPackets.get(0).getBytes(), equalTo(exp));
        assertThat(generatedPackets.get(1).getBytes(), equalTo(firstOne));
    }

    @Test
    public void It_should_keep_track_of_previous_bytes()
    {
        //if a buffer consists of half of a message and another
        //received buffer consists of second half. unibo should create
        //a full packet.
        generatedPackets = convertor.generatePackets(firstHalf);
        assertThat(generatedPackets.size(), equalTo(0)); //this is the first half so size must be 0
        //now send second half
        generatedPackets = convertor.generatePackets(secondHalf);

        assertThat(generatedPackets.get(0).getBytes(), equalTo(fullHalf));
    }

    @Test
    public void Now_everything_together()
    {
        //now we send whole data and check if unibo gets us correct number of packets
        int totalNumberOfPackets = 6;
        generatedPackets = convertor.generatePackets(mixed);
        int numberOfReceivedPackets = generatedPackets.size();

        assertThat(numberOfReceivedPackets, equalTo(totalNumberOfPackets));
    }
}