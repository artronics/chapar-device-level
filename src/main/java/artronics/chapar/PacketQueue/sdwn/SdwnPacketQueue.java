package artronics.chapar.PacketQueue.sdwn;

import artronics.chapar.PacketQueue.AbstractBasePacketQueue;
import artronics.chapar.packet.sdwn.SdwnPacket;

public class SdwnPacketQueue extends AbstractBasePacketQueue<SdwnPacket>
{
    public SdwnPacketQueue()
    {
        super();
    }

    @Override
    public void putInput(SdwnPacket packet)
    {
        try {
            inQueue.put(packet);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
