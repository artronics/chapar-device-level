package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.Packet;
import it.unibo.sdwn.node.Node;

public abstract class BaseTransport implements Transport, Runnable
{

    protected BaseTransport()
    {
    }


    @Override
    public void sendPacket(Packet packet, Node destinatioNode)
    {

    }

    @Override
    public Packet receivePacket(Node destinationNode)
    {
        return null;
    }
}
