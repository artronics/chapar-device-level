package artronics.chapar.trasport.serialCom;

import artronics.chapar.app.event.Event;
import artronics.chapar.app.logger.Log;
import artronics.chapar.trasport.AbstractBaseConnection;
import artronics.chapar.trasport.events.SinkFoundEvent;
import gnu.io.*;
import artronics.chapar.app.config.Config;
import artronics.chapar.trasport.events.ConnectionDataAvailableEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

public class ComConnection extends AbstractBaseConnection implements SerialPortEventListener
{
    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier;
    private SerialPort serialPort = null;
    private InputStream input = null;
    private OutputStream output = null;

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent)
    {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                final byte[] buff = new byte[MAX_PACKET_BUFF];
                final int a = input.read(buff, 0, MAX_PACKET_BUFF);
                ConnectionDataAvailableEvent event = new ConnectionDataAvailableEvent(this, buff, a);
                fireConnectionDataAvailable(event);

            }catch (IOException e) {
                Log.main().error("Can not open IO in ComConnection.");
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void fireConnectionDataAvailable(ConnectionDataAvailableEvent event)
    {
        Event.mainBus().post(event);
    }

    @Override
    public void establishConnection()
    {
        CommInitializer commInitializer = new CommInitializer();
        commInitializer.init();
        this.selectedPortIdentifier = commInitializer.getSelectedCommPortIdentifier();
    }

    @Override
    public void fireSinkFoundEvent(SinkFoundEvent event)
    {
        Event.mainBus().post(event);
    }

    //Open port and addByte initEventListener
    @Override
    public void open()
    {
        final CommPort commPort;
        SerialPort serialPort = null;

        try {
            commPort = selectedPortIdentifier.open("SinkPort", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort) commPort;

            int buadRate = Config.get().getInt("SerialCom.BaudRate");
            serialPort.setSerialPortParams(buadRate,
                                           SerialPort.DATABITS_8,
                                           SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);


        }catch (PortInUseException e) {
            Log.main().error("Port in use. Make sure there is no other app using this com port.");
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (serialPort != null) {
            this.serialPort = serialPort;
            initEventListenersAndIO();
            fireSinkFoundEvent(new SinkFoundEvent(this));
        }else
            throw new NullPointerException();
    }

    @Override
    public void close()
    {
        try {
            input.close();
            output.close();
            serialPort.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initEventListenersAndIO()
    {
        try {
            initListener();
            initIOStream();
        }catch (IOException e) {
            Log.main().error("Can not open IO in ComConnection.");
            e.printStackTrace();
        }
    }

    private void initListener()
    {
        try {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }catch (TooManyListenersException e) {
        }
    }

    private void initIOStream() throws IOException
    {
        input = serialPort.getInputStream();
        output = serialPort.getOutputStream();
    }
}
