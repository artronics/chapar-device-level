package artronics.chapar.controller;

import artronics.chapar.address.AbstractBaseAddress;
import artronics.chapar.address.AddressFactory;
import artronics.chapar.app.event.Event;
import artronics.chapar.node.AbstractBaseNode;
import artronics.chapar.node.NodeFactory;
import artronics.chapar.packet.PacketFactory;
import artronics.chapar.routing.Routing;
import artronics.chapar.trasport.TransportService;
import artronics.chapar.trasport.events.SinkFoundEvent;
import com.google.common.eventbus.Subscribe;

import java.util.Hashtable;

public abstract class BaseControllerService<N extends AbstractBaseNode, A extends AbstractBaseAddress>
        implements ControllerService, Runnable
{
    protected final AddressFactory<A> addressFactory;
    protected Hashtable<A, N> networkMap = new Hashtable();
    protected TransportService transport;
    protected Routing routing;
    protected PacketFactory packetFactory;
    protected NodeFactory<N, A> nodeFactory;

    public BaseControllerService(TransportService transport,
                                 Routing routing,
                                 PacketFactory packetFactory,
                                 NodeFactory nodeFactory,
                                 AddressFactory addressFactory)
    {
        this.transport = transport;
        this.routing = routing;
        this.packetFactory = packetFactory;
        this.nodeFactory = nodeFactory;
        this.addressFactory = addressFactory;
        Event.mainBus().register(this);
    }

    /**
     * An abstract contract which grantees updating network map. Although HashTable is a thread safe implementation,
     * this is up to programmer to apply additional synchronization mechanism.
     *
     * @param node    the node
     * @param address the address
     */
    protected abstract void putNodeToNetworkMap(N node, A address);


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
