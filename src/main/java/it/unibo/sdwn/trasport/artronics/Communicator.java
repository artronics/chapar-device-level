package it.unibo.sdwn.trasport.artronics;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.logger.Log;

public class Communicator
{
    final static int TIMEOUT = 2000;
    //this is the object that contains the opened port
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort connection = null;

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
            return serialPort;
        }else throw new NullPointerException();
    }
}
