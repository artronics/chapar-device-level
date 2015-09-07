package artronics.chapar.node;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BaseNodeTest
{
    private Node aNode;
    private Node sameNode;
    private Node notEqNode;
    private Node[] nodes = new Node[4];

    @Before
    public void setUp() throws Exception
    {
        aNode = new BaseNode(13); //factory.createNode(13);
        sameNode = new BaseNode(13);
        notEqNode = new BaseNode(17);
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
    }

    /**************************
     * EQUALS and HASH CODE
     ****************************/

    @Test
    public void Test_not_equal_to_null()
    {
        assertFalse(aNode.equals(null));
    }

    @Test
    public void It_should_be_an_instance_of_BaseNode()
    {
        assertThat(aNode.getClass(), equalTo(BaseNode.class));
    }

    @Test
    public void Equals_general_laws()
    {
        //reflexivity
        assertEquals(aNode, aNode);
        //Symmetry
        assertThat(aNode, equalTo(sameNode));//an then
        assertThat(sameNode, equalTo(aNode));

        assertFalse(aNode.equals(notEqNode));
        assertFalse(notEqNode.equals(aNode));
    }

    @Test
    public void For_equal_nodes_hashCode_must_be_the_same()
    {
        assertThat(aNode.hashCode(), equalTo(sameNode.hashCode()));
        //unibo is not mandatory for two diff nodes to return diff hash but lets test unibo
        assertNotEquals(aNode.hashCode(), notEqNode.hashCode());
    }

    /************************
     * LINK MANIPULATION
     ********************/

    @Test
    public void test_hasLinkTo()
    {
        assertThat(nodes[0].hasLinkTo(nodes[1]), equalTo(true));
        assertThat(nodes[1].hasLinkTo(nodes[0]), equalTo(true));
    }

    @Test
    public void If_we_create_another_node_with_same_address_it_should_NOT_add_link_to_that()
    {
        Node sameNode1 = new BaseNode(1);
        //we didn't add sameNode1 to link but
        //hasLinkTo returns true
        assertTrue(nodes[0].hasLinkTo(sameNode1));
    }

    @Test
    public void Test_getLinkTo()
    {
        Link link01 = (Link) nodes[0].getLinkTo(nodes[1]);
        Link expLink01 = new SimpleLink(nodes[0], nodes[1], 30);

        assertThat(link01, equalTo(expLink01));

        Link link03 = (Link) nodes[0].getLinkTo(nodes[3]);
        assertThat(link03, equalTo(null));
    }

    /**********************
     * GRAPH
     *******************/

    @Test
    public void Test_existance_of_links()
    {

    }
}