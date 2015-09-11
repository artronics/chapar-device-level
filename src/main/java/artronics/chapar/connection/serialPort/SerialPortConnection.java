package artronics.chapar.connection.serialPort;

import artronics.chapar.broker.MessagesInOut;
import artronics.chapar.connection.ConnectionService;
import artronics.chapar.core.configuration.Config;
import artronics.chapar.core.events.MessageInEvent;
import artronics.chapar.core.events.Event;
import artronics.chapar.core.events.MessageOutEvent;
import artronics.chapar.core.logger.Log;
import com.google.common.eventbus.Subscribe;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

class SerialPortConnection implements ConnectionService, SerialPortEventListener
{
    //    private final ArrayBlockingQueue<List> inQueue;
//    private final ArrayBlockingQueue<int[]> outQueue;
    private final MessagesInOut messagesIn;
    private final MessagesInOut messagesOut;
    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier;
    private SerialPort serialPort = null;
    private InputStream input = null;
    private OutputStream output = null;

    public SerialPortConnection(MessagesInOut messagesIn, MessagesInOut messagesOut)
    {
        this.messagesIn = messagesIn;
        this.messagesOut = messagesOut;

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
                messagesIn.put(intBuff);

            }catch (IOException e) {
                Log.main().error("Can not open IO in ComConnection.");
                e.printStackTrace();
            }
        }

    }

    @Subscribe
    public void messageOutEventHandler(MessageOutEvent event)
    {
        Log.event().debug("Handling MessageOutEvent");
        List<Integer> message = messagesOut.take();

        try {
            for (int i = 0; i < message.size(); i++) {
                output.write(message.get(i));
            }
        }catch (IOException e) {
            e.printStackTrace();
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
