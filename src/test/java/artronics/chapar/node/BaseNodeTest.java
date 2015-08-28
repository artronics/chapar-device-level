package artronics.chapar.node;

import artronics.chapar.address.BaseAddress;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseNodeTest
{
    BaseNodeFactory factory;
    BaseNode node1;
    BaseNode sameNode1;
    BaseAddress add1;
    BaseAddress eqAdd1;
    Quality aQuality = new LinkQuality(45);

    //A graph of nodes
    private Node[] nodes = new Node[4];

    @Before
    public void setUp() throws Exception
    {
        add1 = new BaseAddress(1);
        eqAdd1 = new BaseAddress(1);
        factory = new BaseNodeFactory();
        node1 = factory.createNode(add1);
        sameNode1 = factory.createNode(eqAdd1);
        /*
        we are going to test a simple network consists of four NormalNodes
        These nodes are stores in a node array and network graph is
        like this: (numbers are array indexes and address)
        0<->1 ::value for Quality:30
        1<->2 ::value for Quality:40
        0<->2 ::value for Quality:200
        3<->1 ::value for Quality:100
        */
        Quality q30 = new LinkQuality(30);
        Quality q40 = new LinkQuality(40);
        Quality q200 = new LinkQuality(200);
        Quality q100 = new LinkQuality(100);
        nodes[0] = factory.createNode(new BaseAddress(0));
        nodes[1] = factory.createNode(new BaseAddress(1));
        nodes[2] = factory.createNode(new BaseAddress(2));
        nodes[3] = factory.createNode(new BaseAddress(3));

        nodes[0].addLinkTo(nodes[1], q30);
        nodes[1].addLinkTo(nodes[2], q40);
        nodes[0].addLinkTo(nodes[2], q200);
        nodes[3].addLinkTo(nodes[0], q100);
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
        assertEquals(node1, sameNode1);
        //symmetry
        assertEquals(sameNode1, node1);
        //unequality
        Node notEqAddrNode = factory.createNode(new BaseAddress(3));
        assertNotEquals(node1, notEqAddrNode);
        assertNotEquals(notEqAddrNode, node1);
    }

    @Test
    public void For_equal_nodes_hashCode_must_be_the_same()
    {
        assertEquals(node1.hashCode(), sameNode1.hashCode());
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

    @Test
    public void If_we_create_another_node_with_same_address_it_should_NOT_add_link_to_that()
    {
        //we didn't add sameNode1 to link but
        //hasLinkTo returns true
        assertTrue(nodes[0].hasLinkTo(sameNode1));
    }

    /**********************
     * GRAPH
     *******************/
    @Test
    public void Test_existance_of_links()
    {

    }
}
