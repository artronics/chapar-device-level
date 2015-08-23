package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.SdwnPacket;
import it.unibo.sdwn.node.Node;

public interface Transport
{
    public void init();

    public Connection getConnection();

    public void sendPacket(SdwnPacket sdwnPacket, Node destinatioNode);

    public SdwnPacket receivePacket(Node destinationNode);
}
