package it.unibo.sdwn.controller;

import it.unibo.sdwn.Packet.PacketFactory;
import it.unibo.sdwn.node.NodeFactory;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.TransportService;

public abstract class BaseControllerService implements ControllerService, Runnable
{
    protected TransportService transport;
    protected Routing routing;
    protected PacketFactory packetFactory;
    protected NodeFactory nodeFactory;

    public BaseControllerService(TransportService transport,
                                 Routing routing,
                                 PacketFactory packetFactory,
                                 NodeFactory nodeFactory)
    {
        this.transport = transport;
        this.routing = routing;
        this.packetFactory = packetFactory;
        this.nodeFactory = nodeFactory;
    }

    public void init()
    {
        transport.init();
        routing.init();
    }

    @Override
    public void run()
    {
    }
}
