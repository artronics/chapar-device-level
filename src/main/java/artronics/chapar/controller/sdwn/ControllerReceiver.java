package artronics.chapar.controller.sdwn;


import artronics.chapar.packet.sdwn.SdwnPacket;

import java.util.concurrent.ArrayBlockingQueue;

public class ControllerReceiver implements Runnable
{
    private final ArrayBlockingQueue<SdwnPacket> inPacketQueu;

    public ControllerReceiver(ArrayBlockingQueue<SdwnPacket> inPacketQueu)
    {
        this.inPacketQueu = inPacketQueu;
    }

    @Override
    public void run()
    {
        System.out.println("kir tush");
        while (true) {
            while (!inPacketQueu.isEmpty()) {
                try {
                    processInput(inPacketQueu.take());
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void processInput(SdwnPacket packet)
    {

    }
}
