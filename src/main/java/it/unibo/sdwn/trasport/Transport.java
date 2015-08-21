package it.unibo.sdwn.trasport;

import it.unibo.sdwn.Packet.SdwnPacket;
import it.unibo.sdwn.node.INode;

public interface Transport
{
    public void init();

    public Connection getConnection();

    public void sendPacket(SdwnPacket sdwnPacket, INode destinatioNode);

    public SdwnPacket receivePacket(INode destinationNode);
}
