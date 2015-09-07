package artronics.chapar.map;

import artronics.chapar.node.BaseLink;
import artronics.chapar.node.SimpleNode;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.SimpleGraph;

public class SimpleNetworkMap extends SimpleGraph<SimpleNode,BaseLink>
{
    public SimpleNetworkMap(EdgeFactory ef)
    {
        super(ef);
    }

    public SimpleNetworkMap(Class edgeClass)
    {
        super(edgeClass);
    }
}
