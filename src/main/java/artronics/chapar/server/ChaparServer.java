package artronics.chapar.server;

import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.PacketReceivedEvent;
import artronics.chapar.device.serialPort.DeviceConnectionSerialPort;
import artronics.chapar.device.serialPort.SerialPortConnection;
import artronics.chapar.device.serialPort.SerialPortSetting;
import com.google.common.eventbus.Subscribe;
import com.sun.xml.internal.xsom.impl.scd.Iterators;

import java.util.*;

public class ChaparServer
{
    private final List<List<Integer>> receivedPackets = new LinkedList<>();

    public ChaparServer()
    {
        Event.mainBus().register(this);
    }

    public void sendPacket(List<Integer> packet){

    }

    @Subscribe
    public void receivePacket(PacketReceivedEvent event){
        receivedPackets.add(event.getPacket());
        System.out.println("kir e khar");

        System.out.println(event.getPacket().get(0));
    }

    public void start(){
        SerialPortSetting setting = new SerialPortSetting();
        DeviceConnectionSerialPort conDv = new DeviceConnectionSerialPort();
        Hashtable<String,SerialPortConnection> ports = conDv.getConnections();
        SerialPortConnection conn = ports.get(setting.getComPort());
        conn.setSetting(setting);
        conDv.open(conn);
    }
}
