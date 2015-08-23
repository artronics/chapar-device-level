package it.unibo.sdwn.trasport.artronics;

import gnu.io.*;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.events.TransportDataIsAvailableEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.TooManyListenersException;

public class Communicator implements SerialPortEventListener
{
    final static int TIMEOUT = 2000;
    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    public void init()
    {
        CommInitializer commInitializer = new CommInitializer();
        commInitializer.init();
        this.selectedPortIdentifier = commInitializer.getSelectedCommPortIdentifier();
    }

    public CommPortIdentifier getSelectedPortIdentifier()
    {
        return this.selectedPortIdentifier;
    }

    //Open port and addByte initEventListener
    public SerialPort openPort(CommPortIdentifier commPortIdentifier)
    {
        CommPort commPort;
        SerialPort serialPort = null;

        try {
            commPort = commPortIdentifier.open("SinkPort", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort) commPort;

            int buadRate = Config.get().getInt("SerialCom.BaudRate");
            serialPort.setSerialPortParams(buadRate,
                                           SerialPort.DATABITS_8,
                                           SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);


        }catch (PortInUseException e) {
            e.printStackTrace();
            Log.main().error("If you are on Mac, create a /var/lock with 777 permision." +
                                     " see: http://pizzaprogramming.blogspot.it/2010/05/gnuioportinuseexception.html");
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (serialPort != null) {
            this.serialPort = serialPort;
            initListener();
            return serialPort;
        }else throw new NullPointerException();
    }

    private void initListener()
    {
        try {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            Event.mainBus().register(this);
        }catch (TooManyListenersException e) {
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent)
    {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                InputStream in = this.serialPort.getInputStream();
                //let's fire another event in our own event bus
                //and delegate it to Transport layer.
//                ObjectInput dataObjectIn = new ObjectInputStream(in);
//                Object dataObject = dataObjectIn.readObject();
                //This event should fire when you get data from
                //the lowest level (ie. hardware)
                TransportDataIsAvailableEvent event =
                        new TransportDataIsAvailableEvent(this);
                byte[] byteStream = new byte[10];
                in.read(byteStream);
                event.setData(byteStream);
                event.setDataType(InputStream.class);

                Event.mainBus().post(event);

            }catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("huraaa");
        }

    }
}
