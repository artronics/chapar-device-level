package it.unibo.sdwn.trasport.artronics;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import it.unibo.sdwn.trasport.Connection;

public class ComConnection implements Connection<SerialPort>
{
    private CommPortIdentifier commPortIdentifier;
    private Communicator communicator;
    private SerialPort serialPort;

    public ComConnection()
    {
        Communicator communicator = new Communicator();
        communicator.init();
        this.commPortIdentifier = communicator.getSelectedPortIdentifier();
        this.communicator = communicator;
    }

    @Override
    public void open()
    {
        this.serialPort = communicator.openPort(this.commPortIdentifier);
    }

    @Override
    public void close()
    {
        this.serialPort.removeEventListener();
        this.serialPort.close();
    }

    @Override
    public SerialPort getConnection()
    {
        return this.serialPort;
    }
}
