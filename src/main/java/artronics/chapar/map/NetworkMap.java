package artronics.chapar.map;

import artronics.chapar.node.Node;
import artronics.chapar.packet.Packet;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NetworkMap
{
    private static final int HISTORY_CAPACITY = 1000;
    BlockingQueue<Node> networkMapHistory = new ArrayBlockingQueue(HISTORY_CAPACITY);

    private static class NetworkMapLoader
    {
        private static final NetworkMap INSTANCE = new NetworkMap();
    }

    private NetworkMap()
    {
        if (NetworkMapLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public NetworkMap getNetworkMap()
    {
        return NetworkMapLoader.INSTANCE;
    }

    public void addNode(Node node)
    {
        networkMapHistory.add(node);
    }
}
