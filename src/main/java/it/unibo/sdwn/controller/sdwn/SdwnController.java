package it.unibo.sdwn.controller.sdwn;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.controller.BaseControllerService;
import it.unibo.sdwn.node.NodeFactory;
import it.unibo.sdwn.node.sdwn.SdwnAddress;
import it.unibo.sdwn.node.sdwn.SdwnNode;
import it.unibo.sdwn.packet.PacketFactory;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.TransportService;
import it.unibo.sdwn.trasport.events.SinkFoundEvent;

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
