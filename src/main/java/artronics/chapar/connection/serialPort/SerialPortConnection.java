package artronics.chapar.connection.serialPort;

import artronics.chapar.broker.MessagesInOut;
import artronics.chapar.connection.ConnectionService;
import artronics.chapar.core.configuration.Config;
import artronics.chapar.core.events.DataInEvent;
import artronics.chapar.core.events.Event;
import artronics.chapar.core.logger.Log;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.TooManyListenersException;

final class SerialPortConnection implements ConnectionService, SerialPortEventListener
{
    //    private final ArrayBlockingQueue<List> inQueue;
//    private final ArrayBlockingQueue<int[]> outQueue;
    private final MessagesInOut inOutQueue;
    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier;
    private SerialPort serialPort = null;
    private InputStream input = null;
    private OutputStream output = null;

    public SerialPortConnection(MessagesInOut inOutQueue)
    {
        this.inOutQueue = inOutQueue;
        Event.mainBus().register(this);
    }

    @Override
    public void establishConnection()
    {
        SerialPortInitializer initializer = new SerialPortInitializer();
        initializer.init();
        this.selectedPortIdentifier = initializer.getSelectedCommPortIdentifier();
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent)
    {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                final byte[] buff = new byte[MAX_DATA_LENGTH];
                final int length = input.read(buff, 0, MAX_DATA_LENGTH);
                ArrayList<Integer> intBuff = new ArrayList<>(MAX_DATA_LENGTH);
                for (int i = 0; i < length; i++) {
                    //convert signed value to unsigned
                    intBuff.add(buff[i] & 0xFF);
                }
                inOutQueue.put(intBuff);
                Event.mainBus().post(new DataInEvent());

            }catch (IOException e) {
                Log.main().error("Can not open IO in ComConnection.");
                e.printStackTrace();
            }
        }

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
}
