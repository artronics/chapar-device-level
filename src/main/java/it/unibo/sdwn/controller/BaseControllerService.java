package it.unibo.sdwn.controller;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.Packet.PacketFactory;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.node.AbstractBaseAddress;
import it.unibo.sdwn.node.AbstractBaseNode;
import it.unibo.sdwn.node.NodeFactory;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.TransportService;
import it.unibo.sdwn.trasport.events.SinkFoundEvent;

import java.util.Hashtable;

public abstract class BaseControllerService<N extends AbstractBaseNode, A extends AbstractBaseAddress>
        implements ControllerService, Runnable
{
    protected Hashtable<A, N> networkMap = new Hashtable();
    protected TransportService transport;
    protected Routing routing;
    protected PacketFactory packetFactory;
    protected NodeFactory<N, A> nodeFactory;

    public BaseControllerService(TransportService transport,
                                 Routing routing,
                                 PacketFactory packetFactory,
                                 NodeFactory nodeFactory)
    {
        this.transport = transport;
        this.routing = routing;
        this.packetFactory = packetFactory;
        this.nodeFactory = nodeFactory;
        Event.mainBus().register(this);
    }

    /**
     * An abstract contract which grantees updating network map. Although HashTable is a thread safe implementation,
     * this is up to programmer to apply additional synchronization mechanism.
     *
     * @param node    the node
     * @param address the address
     */
    protected abstract void updateNetworkMap(N node, A address);


    @Subscribe
    abstract public void sinkFoundEventHandler(SinkFoundEvent event);

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
