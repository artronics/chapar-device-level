package artronics.chapar.node;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
public class NodeLinkTest
{
    NodeLink link12;
    NodeLink link23;
    NodeLink link31;
    NodeLink sameLink12;
    private BaseNodeFactory factory = new BaseNodeFactory();
    private Node aNode;
    private Node newNode1;
    private Node newNode2;
    private Node node1;
    private Node node2;
    private Node node3;
    private Quality aQuality = LinkQualityFactory.create(78);

    @Before
    public void setUp() throws Exception
    {
        aNode = factory.createNode(13);
        newNode1 = factory.createNode(1);
        newNode2 = factory.createNode(2);

        node1 = factory.createNode(1);
        node2 = factory.createNode(2);
        node3 = factory.createNode(3);

        link12 = new NodeLink(node1, node2, aQuality);
        sameLink12 = new NodeLink(node1, node2, aQuality);
        link23 = new NodeLink(node2, node3, aQuality);
        link31 = new NodeLink(node3, node1, aQuality);
    }

    /****************
     * TEST EQUALITY
     ***************/

    @Test
    public void Test_not_null_equality()
    {
        assertNotNull(link12);
    }

    @Test
    public void Two_links_are_equal_if_has_equal_quality_and_has_equal_end_nodes()
    {
        //reflexivity
        assertThat(link12, equalTo(link12));

        assertThat(link12, equalTo(sameLink12));
        assertThat(sameLink12, equalTo(link12));
    }

    @Test
    public void Two_links_are_unEqual_if_they_have_different_quality()
    {
        sameLink12.setQuality(LinkQualityFactory.create(90));
        assertNotEquals(link12, sameLink12);
        //what if we again chage unibo to aQuality= 78
        sameLink12.setQuality(LinkQualityFactory.create(78));
        assertThat(sameLink12, equalTo(link12));
    }

    @Test
    public void There_is_no_difference_between_source_and_dest_nodes()
    {
        NodeLink newLink = new NodeLink(node2, node1, aQuality);
        assertThat(newLink, equalTo(link12));
    }

    @Test
    public void It_should_test_just_for_addresses_even_when_two_ends_are_other_instances()
    {
        NodeLink newLink = new NodeLink(newNode1, newNode2, aQuality);
        assertThat(newLink, equalTo(link12));
        assertThat(link12, equalTo(newLink));
        //now lets exchange nodes order
        NodeLink newLink1 = new NodeLink(newNode2, newNode1, aQuality);
        assertThat(newLink1, equalTo(link12));
        assertThat(link12, equalTo(newLink1));
        //lets test hashCode here!
        assertThat(newLink.hashCode(), equalTo(link12.hashCode()));
        assertThat(link12.hashCode(), equalTo(newLink.hashCode()));
        assertThat(newLink1.hashCode(), equalTo(link12.hashCode()));
        assertThat(link12.hashCode(), equalTo(newLink1.hashCode()));
    }

    @Test
    public void If_one_node_is_eq_but_the_other_not_link_is_not_equal()
    {
        NodeLink newLink = new NodeLink(node1, aNode, aQuality);
        assertNotEquals(link12, newLink);
        assertNotEquals(newLink, link12);
    }

    /****************
     * TEST HASH CODE
     ***************/

    @Test
    public void Two_equal_link_must_return_same_hash_code()
    {
        assertThat(link12.hashCode(), equalTo(sameLink12.hashCode()));
    }

    @Test
    public void If_source_and_dest_swaps_it_should_give_same_hash()
    {
        NodeLink newLink = new NodeLink(node2, node1, aQuality);
        assertThat(newLink.hashCode(), equalTo(newLink.hashCode()));
    }

    @Test
    public void Test_for_not_equal_situation_hashcode()
    {
        //change the quality. hash code must change as well
        sameLink12.setQuality(LinkQualityFactory.MAX_QUALITY);
        assertNotEquals(sameLink12.hashCode(), link12.hashCode());

        NodeLink newLink = new NodeLink(node1, aNode, aQuality);
        assertNotEquals(newLink.hashCode(), link12.hashCode());
    }

    /************************
     * LINK MANIPULATION
     ********************/

    @Test
    public void Test_hasNode()
    {
        assertThat(link12.hasNode(node1), equalTo(true));
        assertThat(link12.hasNode(node2), equalTo(true));
        assertFalse(link12.hasNode(node3));
    }

    @Test
    public void HasNode_should_check_for_adderess()
    {
        //we didn't add sameNode1 to link
        //but since unibo has same address of
        //node1 unibo should return true
        assertThat(link12.hasNode(newNode1), equalTo(true));
    }

}