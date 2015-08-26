package it.unibo.sdwn.controller.sdwn;

import it.unibo.sdwn.Packet.PacketFactory;
import it.unibo.sdwn.controller.BaseControllerService;
import it.unibo.sdwn.node.NodeFactory;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.TransportService;

public class SdwnController extends BaseControllerService
{
    public SdwnController(TransportService transport, Routing routing, PacketFactory packetFactory,
                          NodeFactory nodeFactory)
    {
        super(transport, routing, packetFactory, nodeFactory);
    }
}
