package artronics.chapar.node;

import artronics.chapar.address.BaseAddress;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeLinkTest
{
    //These nodes configure a graph
    //which is like a triangle with
    //duplicated link and node to test equality
    BaseNodeFactory factory = new BaseNodeFactory();
    BaseNode node1;
    BaseNode node2;
    BaseNode node3;
    BaseNode sameNode1;
    BaseNode sameNode2;
    NodeLink link12;
    NodeLink link23;
    NodeLink link31;
    NodeLink duplicatedLink12;
    NodeLink sameLink12 = link12;

    @Before
    public void setUp() throws Exception
    {

        node1 = factory.createNode(new BaseAddress(1));
        node2 = factory.createNode(new BaseAddress(2));
        node3 = factory.createNode(new BaseAddress(3));

        link12 = new NodeLink(node1, node2, new LinkQuality(1));
        link23 = new NodeLink(node2, node3, new LinkQuality(2));
        link31 = new NodeLink(node3, node1, new LinkQuality(3));

        sameNode1 = factory.createNode(new BaseAddress(1));
        sameNode2 = factory.createNode(new BaseAddress(2));
        duplicatedLink12 = new NodeLink(node1, node2, new LinkQuality(1));

    }

    /****************
     * TEST EQUALITY
     ***************/

    @Test
    public void Test_not_null_equality()
    {
        assertNotEquals(link12, null);
    }

    @Test
    public void Two_links_are_equal_if_has_equal_quality_and_has_equal_end_nodes()
    {

        //reflexsive
        assertTrue(link12.equals(link12));
        //equality
        assertEquals(link12, duplicatedLink12);
        //symmetric
        assertEquals(duplicatedLink12, link12);
    }

    @Test
    public void Two_links_are_unEqual_if_they_have_different_quality()
    {
        duplicatedLink12.setQuality(new LinkQuality(7));
        assertNotEquals(link12, duplicatedLink12);
    }

    @Test
    public void There_is_no_difference_between_source_and_dest_nodes()
    {
        //notice it is 21. it should be equal to 12
        Link duplicatedLink21 = new NodeLink(node2, node1, new LinkQuality(1));
        assertEquals(link12, duplicatedLink21);
    }

    @Test
    public void It_should_test_just_for_addresses_even_when_two_ends_are_other_instances()
    {
        Link dupLink = new NodeLink(sameNode1, sameNode2, new LinkQuality(1));
        assertEquals(link12, dupLink);
        assertEquals(dupLink, link12);
    }

    @Test
    public void It_should_test_for_addresses_for_one_end()
    {
        Node newNode = factory.createNode(new BaseAddress(2));
        Link newDupLink12 = new NodeLink(newNode, node2, new LinkQuality(1));
        assertNotEquals(link12, newDupLink12);
        assertNotEquals(newDupLink12, link12);
        //lets exchange nodes
        Link newDupLink21 = new NodeLink(node2, newNode, new LinkQuality(1));
        assertNotEquals(link12, newDupLink21);
        assertNotEquals(newDupLink21, link12);

    }

    /****************
     * TEST HASH CODE
     ***************/
    @Test
    public void Two_equal_link_must_return_same_hash_code()
    {
        assertEquals(link12.hashCode(), duplicatedLink12.hashCode());
        assertNotEquals(link12.hashCode(), link23.hashCode());
    }

    @Test
    public void If_source_and_dest_swaps_it_should_give_same_hash()
    {
        Link duplicatedLink21 = new NodeLink(node2, node1, new LinkQuality(1));
        assertEquals(link12.hashCode(), duplicatedLink21.hashCode());
    }

    @Test
    public void Test_for_not_equal_situation_hashcode()
    {
        //change the quality. hash code must change as well
        duplicatedLink12.setQuality(new LinkQuality(7));
        assertNotEquals(link12.hashCode(), duplicatedLink12.hashCode());

        Node newNode = factory.createNode(new BaseAddress(2));
        Link newDupLink12 = new NodeLink(newNode, node2, new LinkQuality(1));
        assertNotEquals(link12.hashCode(), newDupLink12.hashCode());
    }

    /************************
     * LINK MANIPULATION
     ********************/
    @Test
    public void Test_hasNode()
    {
        assertTrue(link12.hasNode(node1));
        assertTrue(link12.hasNode(node2));
        assertFalse(link12.hasNode(node3));

    }

    @Test
    public void HasNode_should_check_for_adderess()
    {
        //we didn't add sameNode1 to link
        //but since it has same address of
        //node1 it should return true
        assertTrue(link12.hasNode(sameNode1));
    }
}