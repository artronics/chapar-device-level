package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.Packet;
import it.unibo.sdwn.node.Node;

public interface Transport
{
    public void init();

    public Connection getConnection();

    public void sendPacket(Packet packet, Node destinatioNode);

    public Packet receivePacket(Node destinationNode);
}
