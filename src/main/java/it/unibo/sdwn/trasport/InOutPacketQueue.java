package it.unibo.sdwn.trasport;

import it.unibo.sdwn.helper.UnsignedByte;

public class InOutPacketQueue implements InOutQueue
{
    public InOutPacketQueue()
    {
    }

    @Override
    public void addInput(byte[] buff, int length)
    {
        try {
            inQueue.put(UnsignedByte.toUnsignedByteArray(buff, length));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (byte b : buff) {
            String msg = " " + UnsignedByte.of(b);
            System.out.print(msg);
        }
        System.out.println();
    }
}
