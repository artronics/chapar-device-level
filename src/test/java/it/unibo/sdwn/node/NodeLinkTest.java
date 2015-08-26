package it.unibo.sdwn.node;

import it.unibo.sdwn.node.sdwn.SdwnAddress;
import it.unibo.sdwn.node.sdwn.SdwnNode;
import org.junit.Before;
import org.junit.Test;

public class NodeLinkTest
{
    //These nodes configure a graph
    //which is like a triangle with
    //duplicated link and node to test equality
    Node node1;
    Node node2;
    Node node3;
    Node sameNode1;
    NodeLink link12;
    NodeLink link23;
    NodeLink link31;
    NodeLink duplicatedLink12;

    @Before
    public void setUp() throws Exception
    {
        node1 = new SdwnNode(new SdwnAddress(1));
        node2 = new SdwnNode(new SdwnAddress(2));
        node3 = new SdwnNode(new SdwnAddress(3));

        link12 = new NodeLink(node1, node2, new LinkQuality(1));
        link23 = new NodeLink(node2, node3, new LinkQuality(2));
        link31 = new NodeLink(node3, node1, new LinkQuality(3));

        sameNode1 = new SdwnNode(new SdwnAddress(1));
        duplicatedLink12 = new NodeLink(node1, node2, new LinkQuality(1));
    }

    @Test
    public void Test_equality()
    {

    }
}