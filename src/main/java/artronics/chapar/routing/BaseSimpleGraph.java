package artronics.chapar.routing;

import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.jgrapht.graph.SimpleGraph;

public class BaseSimpleGraph extends SimpleGraph<Node,Link>
{
    public BaseSimpleGraph(Class<? extends Link> aClass)
    {
        super(aClass);
    }
}
