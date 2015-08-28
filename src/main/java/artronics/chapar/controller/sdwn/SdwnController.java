package artronics.chapar.controller.sdwn;

import artronics.chapar.PacketQueue.PacketQueue;
import artronics.chapar.address.AddressFactory;
import artronics.chapar.address.sdwn.SdwnAddress;
import artronics.chapar.app.config.Config;
import artronics.chapar.app.logger.Log;
import artronics.chapar.controller.AbstractBaseControllerService;
import artronics.chapar.node.NodeFactory;
import artronics.chapar.node.sdwn.SdwnNode;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.packet.sdwn.SdwnPacket;
import artronics.chapar.routing.Routing;
import artronics.chapar.trasport.TransportService;
import artronics.chapar.trasport.events.SinkFoundEvent;

public class SdwnController extends AbstractBaseControllerService<SdwnPacket, SdwnNode, SdwnAddress>
{
    public static final int SINK_ADDRESS = Config.get().getInt("SINK_ADDRESS");

    public SdwnController(TransportService transport,
                          Routing routing,
                          PacketFactory packetFactory,
                          PacketQueue packetQueue,
                          NodeFactory nodeFactory,
                          AddressFactory addressFactory)
    {
        super(transport, routing, packetFactory,packetQueue, nodeFactory, addressFactory);
    }

    @Override
    public void sinkFoundEventHandler(SinkFoundEvent event)
    {
        SdwnAddress address = addressFactory.create(SINK_ADDRESS);
        SdwnNode sink = nodeFactory.createSink(address);
        putNodeToNetworkMap(sink, address);
        Log.main().debug("Sink is added to Network Map Successfully.");

        Thread controllerReceiver = new Thread(new ControllerReceiver(packetQueue),"Receiver");
        controllerReceiver.start();
    }

    @Override
    public void putNodeToNetworkMap(SdwnNode node, SdwnAddress address)
    {
        networkMap.put(address, node);
        Log.main().debug("Network Map is updated.");
    }
}
