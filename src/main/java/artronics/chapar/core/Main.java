package artronics.chapar.core;

import artronics.chapar.device.serialPort.DeviceConnectionSerialPort;
import artronics.chapar.device.serialPort.SerialPortConnection;
import artronics.chapar.server.ChaparServer;

import java.util.Set;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("kiir");

        ChaparServer server = new ChaparServer();
        server.start();

    }

}
