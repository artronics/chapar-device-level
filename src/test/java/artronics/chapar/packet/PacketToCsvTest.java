package artronics.chapar.packet;

import artronics.chapar.address.sdwn.SdwnAddress;
import artronics.chapar.helper.UnsignedByte;
import artronics.chapar.packet.protocol.sdwn.FakeSdwnPacketFactory;
import artronics.chapar.packet.protocol.sdwn.SdwnPacketType;
import artronics.chapar.packet.sdwn.SdwnPacket;
import artronics.chapar.packet.sdwn.SdwnPacketFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
public class PacketToCsvTest
{
    private static final String regex = "\\s*\\d++[;:]"; //See Test_regex() last method
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("DependencyInjection.xml");
    private ArrayList<UnsignedByte> pck = new ArrayList<>();
    private SdwnAddress add = new SdwnAddress(10);
    private SdwnPacket packet;
    private String csv = "";
    private String expCsv;
    private PacketFactory<SdwnPacket,SdwnPacketType> factory = context.getBean(SdwnPacketFactory.class);

    @Before
    public void setUp()
    {
        pck = FakeSdwnPacketFactory.buildGoodPacket(SdwnPacketType.MALFORMED);
        expCsv = FakeSdwnPacketFactory.FakeCsv;

        packet = factory.createPacket(pck);
        csv = packet.toCsv();
        //we delete PacketSerialNumber because there is no
        //way to test this static variable.
        csv = csv.replaceFirst(regex, "");
    }

    @Test
    public void It_should_create_csv_string()
    {
        assertEquals(expCsv, csv);
    }

    @Test
    //
    public void Test_static_behaviour()
    {
        ArrayList p1 = FakeSdwnPacketFactory.buildGoodPacket();
        SdwnPacket pk1 = factory.createPacket(p1);
        String actCsv = pk1.toCsv();
        actCsv = actCsv.replaceFirst(regex, "");
        assertEquals(expCsv, actCsv);

        ArrayList p2 = FakeSdwnPacketFactory.buildGoodPacket(SdwnPacketType.BEACON);
        SdwnPacket pk2 = factory.createPacket(p2);
        actCsv = pk2.toCsv();
        actCsv = actCsv.replaceFirst(regex, "");
        expCsv = FakeSdwnPacketFactory.FakeCsvForBeacon;
        assertEquals(expCsv, actCsv);
    }

    //it's for testing regex in other methods. it has nothing to do with class test
    @Test
    public void Test_regex()
    {
        String act1 = "DATA; IN; 12; 0xFF; 0x12; 11";
        String exp = "DATA; IN; 0xFF; 0x12; 11";
        String regex = "\\s*\\d++[;:]";
//        String act = act1.replace(regex,"");
        String act = act1.replaceFirst(regex, "");
        assertEquals(exp, act);
    }

}