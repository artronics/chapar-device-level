package artronics.chapar.routing;

import artronics.chapar.node.BaseNode;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class NetworkSimpleGraphTest
{

    private Node[] nodes = new Node[4];
    private List<Link> shortestPath03;

    @Before
    public void setUp() throws Exception
    {
        /*
        we are going to test a simple network consists of four NormalNodes
        These nodes are stores in a node array and network graph is
        like this: (numbers are array indexes and address)
        0<->1 ::value for Weigth:30
        1<->2 ::value for Weigth:40
        0<->2 ::value for Weigth:200
        3<->1 ::value for Weigth:100
        */

        nodes[0] = new BaseNode(0);
        nodes[1] = new BaseNode(1);
        nodes[2] = new BaseNode(2);
        nodes[3] = new BaseNode(3);

        nodes[0].addLinkTo(nodes[1], 30);
        nodes[1].addLinkTo(nodes[2], 40);
        nodes[0].addLinkTo(nodes[2], 200);
        nodes[3].addLinkTo(nodes[0], 100);

//        shortestPath03.add(nodes[0].g);

    }

    @Test
    public void It_should_give_shortest_path(){
        NetworkSimpleGraph graph = new NetworkSimpleGraph();
        List<Link> edges =  graph.getShortestPath(nodes[0],nodes[3]);
        assertThat(edges,equalTo(shortestPath03));
    }
}