package it.unibo.sdwn.node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NormalNodeTest
{
    private Node[] nodes = new Node[4];

    @Before
    public void setUp()
    {
        /*
            we are going to test a simple network consists of four NormalNodes
            These nodes are stores in a node array and network graph is
            like this: (numbers are array indexes and address)
            0<->1 ::value for Quality:30
            1<->2 ::value for Quality:40
            0<->2 ::value for Quality:200
            3<->1 ::value for Quality:100
         */

        nodes[0] = new NormalNode(0);
        nodes[1] = new NormalNode(1);
        nodes[2] = new NormalNode(2);
        nodes[3] = new NormalNode(3);

        //since communication is full/half duplex we indicate one way link
        nodes[0].addLinkTo(nodes[1],new LinkQuality(30));
        nodes[0].addLinkTo(nodes[2], new LinkQuality(200));
        nodes[1].addLinkTo(nodes[2], new LinkQuality(40));
        nodes[3].addLinkTo(nodes[1], new LinkQuality(100));

    }

    @Test
    public void test_hasLinkTo()
    {
        boolean condition = nodes[0].hasLinkTo(nodes[1]);

        assertTrue(condition);
    }

    @Test
    public void It_should_create_link_in_opposite_direction_in_HALF_or_FULL_DUPLEX()
    {
        boolean condition = nodes[1].hasLinkTo(nodes[0]);

        assertTrue(condition);
    }

    @Test
    public void Quality_is_reciprocal(){
        Quality node0Quality = nodes[0].getLinkTo(nodes[1]).getQuality();
        Quality node1Quality = nodes[1].getLinkTo(nodes[0]).getQuality();

        assertEquals(node0Quality,node1Quality);
    }

    @Test
    public void DestinationNode_in_a_link_must_have_a_reference_to_node()
    {
        //it means if for example we change address of a node
        //all destinationNodes in link must conform to this change
        //You can also consider this as a test for getLinkT
        nodes[0].setAddress(new Address(10));
        Link linkTo = nodes[1].getLinkTo(nodes[0]);
        Node destinationNode = linkTo.getDestinationNode();
        Address actualAddr = destinationNode.getAddress();

        assertEquals(nodes[0].getAddress(), actualAddr);
    }

}