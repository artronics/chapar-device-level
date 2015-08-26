package it.unibo.sdwn.node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseNodeTest
{
    NodeFactory factory;
    Node node1;
    Node eqNode1;
    Address add1;
    Address eqAdd1;

    //A graph of nodes
    private Node[] nodes = new Node[4];

    @Before
    public void setUp() throws Exception
    {
        add1 = new BaseAddress(1);
        eqAdd1 = new BaseAddress(1);
        factory = new BaseNodeFactory();
        node1 = factory.createNode(add1);
        eqNode1 = factory.createNode(eqAdd1);
        /*
        we are going to test a simple network consists of four NormalNodes
        These nodes are stores in a node array and network graph is
        like this: (numbers are array indexes and address)
        0<->1 ::value for Quality:30
        1<->2 ::value for Quality:40
        0<->2 ::value for Quality:200
        3<->1 ::value for Quality:100
        */
        nodes[0] = factory.createNode(new BaseAddress(0));
        nodes[1] = factory.createNode(new BaseAddress(1));
        nodes[2] = factory.createNode(new BaseAddress(2));
        nodes[3] = factory.createNode(new BaseAddress(3));
    }

    /**************************
     * EQUALS and HASH CODE
     ****************************/
    @Test
    public void Test_not_equal_to_null()
    {
        assertFalse(node1.equals(null));
    }

    @Test
    public void Two_nodes_are_equal_if_they_have_equal_addresses()
    {
        //reflexsive
        assertEquals(node1, node1);
        assertEquals(node1, eqNode1);
        //symmetry
        assertEquals(eqNode1, node1);
        //unequality
        Node notEqAddrNode = factory.createNode(new BaseAddress(3));
        assertNotEquals(node1, notEqAddrNode);
        assertNotEquals(notEqAddrNode, node1);
    }

    @Test
    public void For_equal_nodes_hashCode_must_be_the_same()
    {
        assertEquals(node1.hashCode(), eqNode1.hashCode());
        Node notEqAddrNode = factory.createNode(new BaseAddress(3));
        assertNotEquals(node1.hashCode(), notEqAddrNode.hashCode());
        assertNotEquals(notEqAddrNode.hashCode(), node1.hashCode());
    }

    /************************
     * LINK MANIPULATION
     ********************/
    @Test
    public void test_hasLinkTo()
    {
        boolean condition = nodes[0].hasLinkTo(nodes[1]);

        assertTrue(condition);
    }
}
/*

    @Before
    public void setUp()
    {

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
        Address actualAddr = destinationNode.intValue();

        assertEquals(nodes[0].intValue(), actualAddr);
    }
}
*/
