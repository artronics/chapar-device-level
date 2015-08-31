package artronics.chapar.controller;


import artronics.chapar.core.events.Event;
import artronics.chapar.node.LinkQualityFactory;
import artronics.chapar.node.NodeFactory;
import artronics.chapar.packet.PacketContract;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.queue.DataInOutQueueContract;
import artronics.chapar.queue.PacketInOutQueueContract;
import artronics.chapar.routing.RoutingService;

import java.util.concurrent.ArrayBlockingQueue;

class AbstractBaseController
{
    private final PacketFactory packetFactory;
    private final NodeFactory nodeFactory;
    private final LinkQualityFactory linkQualityFactory;
    private final RoutingService router;
    private final PacketInOutQueueContract packetQueue;

    private final ArrayBlockingQueue<PacketContract> packetInQueue;
    private final ArrayBlockingQueue<PacketContract> packetOutQueue;

    public AbstractBaseController(PacketFactory packetFactory,
                                  NodeFactory nodeFactory,
                                  LinkQualityFactory linkQualityFactory,
                                  RoutingService router,
                                  PacketInOutQueueContract packetQueue)
    {
        this.packetFactory = packetFactory;
        this.nodeFactory = nodeFactory;
        this.linkQualityFactory = linkQualityFactory;
        this.router = router;
        this.packetQueue = packetQueue;

        this.packetInQueue = packetQueue.getPacketInQueue();
        this.packetOutQueue = packetQueue.getPacketOutQueue();

        Event.mainBus().register(this);
    }
}
