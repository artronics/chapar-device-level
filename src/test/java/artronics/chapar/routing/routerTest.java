package artronics.chapar.routing;

import artronics.chapar.map.BaseNetworkMap;
import artronics.chapar.map.NetworkMap;
import artronics.chapar.node.BaseLink;
import artronics.chapar.node.BaseNode;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class RouterTest
{
    NetworkMap networkMap;
    Router router ;

    Node node0;
    Node node1;
    Node node2;
    Node node3;

    @Before
    public void setUp() throws Exception
    {
        networkMap = BaseNetworkMap.getInstance();

        router = new Router(networkMap);

        /*
            A simple graph for testing Router
            node0 --30--> node1
            node2 --05--> node1
            node2 --10--> node0
            node3 --50--> node1

            The shortest path from node0 to node3 is:
                node0
                node2
                node1
                node3
            Remember NetworkMap is not a directed graph.
         */

        node0 = new BaseNode(0);
        node1 = new BaseNode(1);
        node2 = new BaseNode(2);
        node3 = new BaseNode(3);

        networkMap.addNode(node0);
        networkMap.addNode(node1);
        networkMap.addNode(node2);
        networkMap.addNode(node3);

        networkMap.addLink(node0, node1, 30);
        networkMap.addLink(node2, node1, 5);
        networkMap.addLink(node2, node0, 10);
        networkMap.addLink(node3, node1, 50);
    }

    @Test
    public void It_should_give_the_shortest_path()
    {
        List<Link> path = router.getShortestPath(node0, node3);
        assertThat(path.size(),equalTo(3));

//        assertThat(path.get(0),equalTo(node0));
    }
}