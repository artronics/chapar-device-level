package artronics.chapar.routing;

import artronics.chapar.map.BaseNetworkMap;
import artronics.chapar.map.NetworkMap;
import artronics.chapar.node.BaseNode;
import artronics.chapar.node.Link;
import artronics.chapar.node.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class routerTest
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
        List path = router.getShortestPath(node0,node3);
        assertThat(path.size(),equalTo(3));


    }
}