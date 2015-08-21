package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.SdwnPacket;
import it.unibo.sdwn.node.Node;

public abstract class BaseTransport implements Transport, Runnable
{

    protected BaseTransport()
    {
    }


    @Override
    public void sendPacket(SdwnPacket sdwnPacket, Node destinatioNode)
    {

    }

    @Override
    public SdwnPacket receivePacket(Node destinationNode)
    {
        return null;
    }
}
