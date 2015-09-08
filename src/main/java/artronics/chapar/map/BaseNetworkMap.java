package artronics.chapar.map;

import artronics.chapar.node.*;
import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class BaseNetworkMap<N extends AbstractNode> extends AbstractNetworkMap<N,BaseLink>
{
    public BaseNetworkMap()
    {
        super(BaseLink.class);
    }

    @Override
    public void addLink(N source, N target, double weight)
    {
        BaseLink link = this.graph.addEdge(source, target);

        if (link != null) {
            this.graph.setEdgeWeight(link, weight);
        }
    }
}
