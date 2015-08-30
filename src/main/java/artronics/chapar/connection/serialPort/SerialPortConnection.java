package artronics.chapar.connection.serialPort;

import artronics.chapar.connection.Connection;
import artronics.chapar.core.configuration.Config;
import artronics.chapar.core.logger.Log;
import gnu.io.*;

import javax.sql.StatementEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

public class SerialPortConnection implements Connection, SerialPortEventListener
{
    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier;
    private SerialPort serialPort = null;
    private InputStream input = null;
    private OutputStream output = null;

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
