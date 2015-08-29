package artronics.chapar.node;

import org.junit.Before;

public class NodeLinkTest
{
    NodeLink link12;
    NodeLink link23;
    NodeLink link31;
    private BaseNodeFactory factory = new BaseNodeFactory();
    private Node aNode;
    private Node sameNode;
    private Node notEqNode;
    private Node node1;
    private Node node2;
    private Node node3;
    private Quality aQuality = LinkQualityFactory.create(78);

    @Before
    public void setUp() throws Exception
    {
        aNode = factory.createNode(13);
        sameNode = factory.createNode(13);
        notEqNode = factory.createNode(17);

        node1 = factory.createNode(1);
        node2 = factory.createNode(2);
        node3 = factory.createNode(3);

        link12 = new NodeLink(node1, node2, aQuality);
        link23 = new NodeLink(node2, node3, aQuality);
        link31 = new NodeLink(node3, node1, aQuality);
    }
}