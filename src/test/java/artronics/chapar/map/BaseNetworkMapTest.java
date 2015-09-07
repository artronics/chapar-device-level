package artronics.chapar.map;

import artronics.chapar.node.BaseNode;
import artronics.chapar.node.Node;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BaseNetworkMapTest
{
    BaseNetworkMap networkMap;

    Node node0;
    Node node1;
    Node node2;
    Node node3;

    @Before
    public void setUp() throws Exception
    {
        networkMap = BaseNetworkMap.getInstance();

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
    public void It_should_return_true_if_a_node_hasLink_to_other_node()
    {
        assertThat(networkMap.hasLink(node0, node1), equalTo(true));
        //reverse dir
        assertThat(networkMap.hasLink(node1, node0), equalTo(true));

        assertThat(networkMap.hasLink(node0, node3), equalTo(false));

    }
    @Test
    public void It_should_return_false_if_we_ask_a_node_hasLink_to_itself()
    {
        assertThat(networkMap.hasLink(node0, node0), equalTo(false));
    }

}