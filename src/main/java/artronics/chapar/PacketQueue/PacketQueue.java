package artronics.chapar.PacketQueue;

import artronics.chapar.packet.AbstractBasePacket;

public interface PacketQueue<P extends AbstractBasePacket>
{

    void putInput(P packet);

    P takeInput();
}

