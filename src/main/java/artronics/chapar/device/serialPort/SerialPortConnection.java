package artronics.chapar.device.serialPort;

import artronics.chapar.device.Connection;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

public class SerialPortConnection implements Connection
{
    private final CommPortIdentifier commPortIdentifier;

    private SerialPortSetting setting;

    private SerialPort serialPort;

    public SerialPortConnection(CommPortIdentifier commPortIdentifier)
    {
        this.commPortIdentifier = commPortIdentifier;
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
        }else
            throw new NullPointerException();
    }

    @Override
    public void close()
    {
        serialPort.close();
    }

    @Override
    public String toString()
    {
        return commPortIdentifier.getName();
    }
}
