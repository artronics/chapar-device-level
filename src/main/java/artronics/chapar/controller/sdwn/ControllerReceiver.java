package artronics.chapar.controller.sdwn;

import artronics.chapar.PacketQueue.PacketQueue;

public class ControllerReceiver implements Runnable
{
    private final PacketQueue packetQueue;

    public ControllerReceiver(PacketQueue packetQueue)
    {
        this.packetQueue = packetQueue;
    }

    @Override
    public void run()
    {
        System.out.println("kir tush");



    }
}
