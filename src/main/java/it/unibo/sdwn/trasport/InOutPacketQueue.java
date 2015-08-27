package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.packet.Packet;

public class InOutPacketQueue implements InOutQueue
{
    public InOutPacketQueue()
    {
        //register this in event bus
        Event.mainBus().register(this);
    }

    @Override
    public void putInput(Packet packet)
    {
        try {
            inQueue.put(packet);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
//        for (UnsignedByte b : packet ) {
//            String msg = " " + b;
//            System.out.print(msg);
//        }
//        System.out.println();
    }
}
