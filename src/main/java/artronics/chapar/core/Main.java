package artronics.chapar.core;

import artronics.chapar.device.serialPort.DeviceConnectionSerialPort;
import artronics.chapar.device.serialPort.SerialPortConnection;

import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("kiir");

        Set<SerialPortConnection> sp = new DeviceConnectionSerialPort().getConnections();
        for (SerialPortConnection c : sp)
            System.out.println(c.toString());

    }

}
