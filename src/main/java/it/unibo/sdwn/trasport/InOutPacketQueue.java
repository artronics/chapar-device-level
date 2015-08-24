package it.unibo.sdwn.trasport;

import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class InOutPacketQueue implements InOutQueue
{
    public InOutPacketQueue()
    {
    }

    @Override
    public void addInput(ArrayList<UnsignedByte> packet)
    {
        try {
            inQueue.put(packet);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (UnsignedByte b : packet ) {
            String msg = " " + b;
            System.out.print(msg);
        }
        System.out.println();
    }
}
