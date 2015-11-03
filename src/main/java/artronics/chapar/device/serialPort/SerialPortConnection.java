package artronics.chapar.device.serialPort;

import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.PacketReceivedEvent;
import artronics.chapar.device.Connection;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TooManyListenersException;

public class SerialPortConnection implements Connection , SerialPortEventListener
{
    private final CommPortIdentifier commPortIdentifier;

    private SerialPortSetting setting;

    private SerialPort serialPort;

    private InputStream input = null;

    private OutputStream output = null;

    public SerialPortConnection(CommPortIdentifier commPortIdentifier)
    {
        this.commPortIdentifier = commPortIdentifier;

        Event.mainBus().register(this);
    }

    public void setSetting(SerialPortSetting setting)
    {
        this.setting = setting;
    }

    @Override
    public void open()
    {
        final CommPort commPort;
        SerialPort serialPort = null;

        try {
            commPort = commPortIdentifier.open("SinkPort", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort) commPort;

            serialPort.setSerialPortParams(setting.getBaudrate(),
                                           SerialPort.DATABITS_8,
                                           SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);


        }catch (PortInUseException e) {
//            Log.main().error("Port in use. Make sure there is no other app using this com port.");
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (serialPort != null) {
            this.serialPort = serialPort;
            initEventListenersAndIO();
        }else
            throw new NullPointerException();
    }

    @Override
    public void close()
    {
        try {

            serialPort.close();
            input.close();
            output.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return commPortIdentifier.getName();
    }

    private void initEventListenersAndIO()
    {
        try {

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();

        }catch (TooManyListenersException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void serialEvent(SerialPortEvent serialPortEvent)
    {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                Integer maxPacketLength = setting.getMaxPacketLength();
                final byte[] buff = new byte[maxPacketLength];
                final int length = input.read(buff, 0, maxPacketLength);
                ArrayList<Integer> intBuff = new ArrayList<>(maxPacketLength);
                for (int i = 0; i < length; i++) {
                    //convert signed value to unsigned
                    intBuff.add(buff[i] & 0xFF);
                }

                PacketReceivedEvent event = new PacketReceivedEvent(intBuff);
                Event.mainBus().post(event);

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
