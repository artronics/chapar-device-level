package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.sdwn.SdwnPacket;
import it.unibo.sdwn.app.event.Event;

public class InOutPacketQueue implements InOutQueue
{
    public InOutPacketQueue()
    {
        //register this in event bus
        Event.mainBus().register(this);
    }

    @Override
    public void putInput(SdwnPacket packet)
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
