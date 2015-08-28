package artronics.chapar.controller.sdwn;

import artronics.chapar.packet.PacketFactory;
import artronics.chapar.app.config.Config;
import artronics.chapar.app.logger.Log;
import artronics.chapar.controller.BaseControllerService;
import artronics.chapar.node.NodeFactory;
import artronics.chapar.node.sdwn.SdwnAddress;
import artronics.chapar.node.sdwn.SdwnNode;
import artronics.chapar.routing.Routing;
import artronics.chapar.trasport.TransportService;
import artronics.chapar.trasport.events.SinkFoundEvent;

public class SdwnController extends BaseControllerService<SdwnNode, SdwnAddress>
{
    public static final int SINK_ADDRESS = Config.get().getInt("SINK_ADDRESS");

    public SdwnController(TransportService transport,
                          Routing routing,
                          PacketFactory packetFactory,
                          NodeFactory nodeFactory)
    {
        super(transport, routing, packetFactory, nodeFactory);
    }

    @Override
    public void sinkFoundEventHandler(SinkFoundEvent event)
    {
        SdwnAddress address = new SdwnAddress(SINK_ADDRESS);
        SdwnNode sink = nodeFactory.createSink(address);
        putNodeToNetworkMap(sink, address);
        Log.main().debug("Sink is added to Network Map Successfully.");
    }

    @Override
    public void putNodeToNetworkMap(SdwnNode node, SdwnAddress address)
    {
        networkMap.put(address, node);
        Log.main().debug("Network Map is updated.");
    }
}
