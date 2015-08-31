package artronics.chapar.controller;


import artronics.chapar.node.LinkQualityFactory;
import artronics.chapar.node.NodeFactory;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.routing.RoutingService;

class AbstractBaseController implements ControllerService
{
    public  PacketFactory packetFactory;
    public  NodeFactory nodeFactory;
    public  LinkQualityFactory linkQualityFactory;
    public  RoutingService router;

    public void setPacketFactory(PacketFactory packetFactory)
    {
        this.packetFactory = packetFactory;
    }

    public void setNodeFactory(NodeFactory nodeFactory)
    {
        this.nodeFactory = nodeFactory;
    }

    public void setLinkQualityFactory(LinkQualityFactory linkQualityFactory)
    {
        this.linkQualityFactory = linkQualityFactory;
    }

    public void setRouter(RoutingService router)
    {
        this.router = router;
    }
}
