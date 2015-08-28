package artronics.chapar.trasport;

import artronics.chapar.app.event.Event;
import artronics.chapar.packet.Packet;

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
