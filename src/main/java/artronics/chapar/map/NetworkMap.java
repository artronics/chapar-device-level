package artronics.chapar.map;

import artronics.chapar.core.analyser.Analysable;
import artronics.chapar.core.analyser.ToCsv;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;

import java.util.*;

public class NetworkMap
{
    private static final int HISTORY_CAPACITY = 1000;
    private static final Comparator<Link> LINK_COMPARATOR = new LinkCmp();
    //use for sorting links in csv
    private final Analysable csv = new CsvWriter();//use for writing to csv
    private final Hashtable<Integer, Node> networkMap = new Hashtable<>();
    private final ArrayDeque<Node> networkMapHistory = new ArrayDeque<>();


    //<editor-fold desc="Singleton">
    private NetworkMap()
    {
        if (NetworkMapLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }
    //</editor-fold>

    public static NetworkMap getNetworkMap()
    {
        return NetworkMapLoader.INSTANCE;
    }

    public Node peekFirstInHistory()
    {
        return networkMapHistory.peekFirst();
    }

    public boolean contains(Integer address)
    {
        return networkMap.contains(address);
    }

    public void updateNetworkMap(Node node)
    {
        Integer address = (Integer) node.getAddress();
        networkMap.put(address, node);

        networkMapHistory.addFirst(node);

        ToCsv.write("NetworkMap", csv);
    }

    public Enumeration getAddresses()
    {
        return networkMap.keys();
    }

    public void addNode(Node node)
    {
        networkMapHistory.add(node);
    }


    private static class LinkCmp
            implements Comparator<Link>
    {
        @Override
        public int compare(Link o1, Link o2)
        {
            Node target1 = o1.getTarget();
            Node target2 = o2.getTarget();
            int target1Addr = (Integer) (target1.getAddress());
            int target2Addr = (Integer) (target2.getAddress());

            if (target1Addr > target2Addr)
                return 1;
            else if (target1Addr < target2Addr)
                return -1;
            else
                return 0;
        }
    }

    private static class NetworkMapLoader
    {
        private static final NetworkMap INSTANCE = new NetworkMap();
    }

    private final class CsvWriter implements Analysable
    {
        @Override
        public String toCsv()
        {
            Node node = peekFirstInHistory();
            int add = (Integer) node.getAddress();
            List links = node.getLinks();
            Collections.sort(links, LINK_COMPARATOR);

            return ToCsv.creat(add, links.size(), links);
        }
    }
}
