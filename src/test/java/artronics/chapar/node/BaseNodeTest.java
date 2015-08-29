package artronics.chapar.node;

import org.junit.Before;

import static org.junit.Assert.*;

public class BaseNodeTest
{
    private BaseNodeFactory factory = new BaseNodeFactory();
    private Node[] nodes = new Node[4];

    @Before
    public void setUp() throws Exception
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
        Quality q30 = LinkQualityFactory.create(30);
        Quality q40 = LinkQualityFactory.create(40);
        Quality q200 = LinkQualityFactory.create(200);
        Quality q100 = LinkQualityFactory.create(100);

        nodes[0] = factory.createNode(0);
        nodes[1] = factory.createNode(1);
        nodes[2] = factory.createNode(2);
        nodes[3] = factory.createNode(3);

    }
}