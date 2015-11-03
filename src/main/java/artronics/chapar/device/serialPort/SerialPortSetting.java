package artronics.chapar.device.serialPort;

import java.io.Serializable;

public class SerialPortSetting implements Serializable
{
    private Integer baudrate;

    public SerialPortSetting()
    {
    }

    public Integer getBaudrate()
    {
        return baudrate;
    }

    public void setBaudrate(Integer baudrate)
    {
        this.baudrate = baudrate;
    }
}
