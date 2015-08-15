package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.Packet;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.node.Node;

public class ComTransport implements Transport
{
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
