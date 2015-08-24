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
        //convert buff elements to UnsignedByte from 0 to length
        UnsignedByte[] unsignedBytes = UnsignedByte.toUnsignedByteArray(buff, length);
        try {
            inQueue.put(unsignedBytes);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (UnsignedByte b : unsignedBytes ) {
            String msg = " " + b;
            System.out.print(msg);
        }
        System.out.println();
    }
}
