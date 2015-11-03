package artronics.chapar.device.serialPort;

import artronics.chapar.device.DeviceConnection;
import gnu.io.CommPortIdentifier;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class DeviceConnectionSerialPort implements DeviceConnection<SerialPortConnection>
{
    private Hashtable<String,SerialPortConnection> ports = new Hashtable<>();

    @Override
    public void establishConnection()
    {


    }

    @Override
    public Hashtable<String,SerialPortConnection> getConnections()
    {
        Enumeration portsEnum = CommPortIdentifier.getPortIdentifiers();

        while (portsEnum.hasMoreElements()){
            CommPortIdentifier port = (CommPortIdentifier) portsEnum.nextElement();
            SerialPortConnection sp=new SerialPortConnection(port);
            this.ports.put(port.getName(), sp);
        }

        return this.ports;
    }

    @Override
    public void open(SerialPortConnection connection)
    {
        connection.open();
    }

    @Override
    public void close(SerialPortConnection connection)
    {
        connection.close();
    }
}
