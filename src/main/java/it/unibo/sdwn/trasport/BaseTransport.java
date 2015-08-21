package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.SdwnPacket;
import it.unibo.sdwn.node.INode;

public abstract class BaseTransport implements Transport, Runnable
{

    protected BaseTransport()
    {
    }


    @Override
    public void sendPacket(SdwnPacket sdwnPacket, INode destinatioNode)
    {

    }

    @Override
    public SdwnPacket receivePacket(INode destinationNode)
    {
        return null;
    }
}
