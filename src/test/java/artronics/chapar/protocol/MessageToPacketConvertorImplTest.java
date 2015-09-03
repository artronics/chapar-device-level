package artronics.chapar.protocol;

import artronics.chapar.FakePacketFactory;
import artronics.chapar.broker.MessageToPacketConvertor;
import artronics.chapar.broker.MessageToPacketConvertorI;
import artronics.chapar.broker.MessagesIn;
import artronics.chapar.packet.Packet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:chapar_DI.xml")
public class MessageToPacketConvertorImplTest
{
    private static final int START_BYTE = FakePacketFactory.START_BYTE;
    private static final int STOP_BYTE = FakePacketFactory.STOP_BYTE;
    final FakePacketFactory factory = new FakePacketFactory();

    //if you resolve this class with DI you can not run
    //bytes test together. I have no reason why this happens
//    @Autowired
//    MessageToPacketConvertor engine ;
    @Autowired
    MessagesIn messagesIn;
    MessageToPacketConvertorI engine = new MessageToPacketConvertor();

    List<Integer> goodPacket;
    List<Packet> generatedPackets;

    List<Integer> firstOne;
    List<Integer> secondOne;
    List<Integer> twoPackets;
    List<Integer> giberish;
    List<Integer> withGibberish;
    List<Integer> withStartStop;
    List<Integer> withStartStop_anotherPacket;
    List<Integer> firstHalf;
    List<Integer> secondHalf;
    List<Integer> fullHalf;
    List<Integer> mixed;

    @Before
    public void setUp() throws Exception
    {
        goodPacket = FakePacketFactory.buildGoodPacket();

        Integer[] streamOfBytes =
                {
                        12, 34, 87, 89,//some giberish ;0-4
                        START_BYTE, 2, 23, STOP_BYTE, //first :4-8
                        678, 85, 73, 75, //again some giberish :8-12
                        START_BYTE, 2, 39, STOP_BYTE, //second :12-16
                        // an appropriate packet with stop and start bytes as data
                        START_BYTE, 3, START_BYTE, STOP_BYTE, STOP_BYTE, //16-21
                        START_BYTE, 2, 23, STOP_BYTE, //another packet after unibo, same as firstPacket 21-25
                        START_BYTE, 6, 45, 9, //A half packet 25-29
                        53, 74, 54, STOP_BYTE, //another half 29-33
                        //Notice that unibo does not validate your packet
                        //if your packet is longer that length your packet is
                        //malformed. if packet is shorter that length
                        //engine conside stop byte as data.
                        START_BYTE, 1, 45, STOP_BYTE //Notice length is less than actual length
                };

        //for those packets which we want to assert we need to remove start and stop bytes
        //for first, second, and fullhalf i changed the index range. for others
        //see removeStartStop method
        Integer[] giberishArr = Arrays.copyOfRange(streamOfBytes, 0, 4);
        Integer[] firstOneArr = Arrays.copyOfRange(streamOfBytes, 5, 7);
        Integer[] secondOneArr = Arrays.copyOfRange(streamOfBytes, 13, 15);
        Integer[] twoPacketsArr = Arrays.copyOfRange(streamOfBytes, 4, 16);
        Integer[] withGibberishArr = Arrays.copyOfRange(streamOfBytes, 0, 16);
        Integer[] withStartStopArr = Arrays.copyOfRange(streamOfBytes, 16, 21);
        Integer[] withStrStp_packet = Arrays.copyOfRange(streamOfBytes, 16, 26);
        Integer[] firstHalfArr = Arrays.copyOfRange(streamOfBytes, 25, 29);
        Integer[] secondHalfArr = Arrays.copyOfRange(streamOfBytes, 29, 33);
        Integer[] fullHalfArr = Arrays.copyOfRange(streamOfBytes, 26, 32);

        firstOne = Arrays.asList(firstOneArr);
        secondOne = Arrays.asList(secondOneArr);
        twoPackets = Arrays.asList(twoPacketsArr);
        giberish = Arrays.asList(giberishArr);
        withGibberish = Arrays.asList(withGibberishArr);
        withStartStop = Arrays.asList(withStartStopArr);
        withStartStop_anotherPacket = Arrays.asList(withStrStp_packet);
        firstHalf = Arrays.asList(firstHalfArr);
        secondHalf = Arrays.asList(secondHalfArr);
        fullHalf = Arrays.asList(fullHalfArr);
        mixed = Arrays.asList(streamOfBytes);
    }

    private static List removeStartStop(List<Integer> packet)
    {
        List exp = packet.subList(1, packet.size() - 1);
        return exp;
    }

    @Test
    public void If_we_pass_a_full_packet_it_should_return_that_as_it_is()
    {
        generatedPackets = engine.generatePackets(goodPacket);

        //first sice we send one packet size must be 1
        assertThat(generatedPackets.size(), equalTo(1));

        //remove start and stop bytes
        List exp = goodPacket.subList(1, goodPacket.size() - 1);
        assertThat(generatedPackets.get(0).getBytes(), equalTo(exp));
    }

    @Test
    public void As_long_as_queue_has_data_it_should_create_packets()
    {
        generatedPackets = engine.generatePackets(twoPackets);
        //first test the size
        assertThat(generatedPackets.size(), equalTo(2));

        assertThat(generatedPackets.get(0).getBytes(), equalTo(firstOne));
        assertThat(generatedPackets.get(1).getBytes(), equalTo(secondOne));
    }

    @Test
    public void It_sould_ignore_un_approprite_bytes_before_start_and_after_stop()
    {
        generatedPackets = engine.generatePackets(withGibberish);
        //first test the size
        assertThat(generatedPackets.size(), equalTo(2));

        assertThat(generatedPackets.get(0).getBytes(), equalTo(firstOne));
        assertThat(generatedPackets.get(1).getBytes(), equalTo(secondOne));

    }

    @Test
    public void If_data_consists_of_start_and_stop_bytes_it_should_not_fuck_up()
    {
        generatedPackets = engine.generatePackets(withStartStop);
        //first test the size
        List exp = removeStartStop(withStartStop);
        assertThat(generatedPackets.size(), equalTo(1));
        assertThat(generatedPackets.get(0).getBytes(), equalTo(exp));

        //now lets add another packet to end of unibo
        generatedPackets = engine.generatePackets(withStartStop_anotherPacket);
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
        generatedPackets = engine.generatePackets(firstHalf);
        assertThat(generatedPackets.size(), equalTo(0)); //this is the first half so size must be 0
        //now send second half
        generatedPackets = engine.generatePackets(secondHalf);

        assertThat(generatedPackets.get(0).getBytes(), equalTo(fullHalf));
    }

    @Test
    public void Now_everything_together()
    {
        //now we send whole data and check if unibo gets us correct number of packets
        int totalNumberOfPackets = 6;
        generatedPackets = engine.generatePackets(mixed);
        int numberOfReceivedPackets = generatedPackets.size();

        assertThat(numberOfReceivedPackets, equalTo(totalNumberOfPackets));
    }
}