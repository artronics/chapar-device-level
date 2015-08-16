package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.Packet;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.node.Node;

public abstract class BaseTransport implements Transport, Runnable
{
    private static Transport transport;

    protected BaseTransport()
    {
    }

    @Override
    public void init()
    {
        Log.main().debug("inside com init");
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
