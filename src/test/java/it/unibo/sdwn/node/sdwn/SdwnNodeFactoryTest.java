package it.unibo.sdwn.node.sdwn;

import it.unibo.sdwn.node.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SdwnNodeFactoryTest
{
    private SdwnNodeFactory factory = new SdwnNodeFactory();

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void It_should_create_normal_node()
    {
        Node n = factory.createNode(new SdwnAddress(10));

        assertEquals(n.getType(), Node.Type.NORMAL);
        assertNotEquals(n.getType(), Node.Type.END_NODE);
    }

    @Test
    public void It_should_create_sink_node()
    {
        Node n = factory.createSink(new SdwnAddress(0));

        assertEquals(n.getType(), Node.Type.SINK);
        assertNotEquals(n.getType(), Node.Type.END_NODE);
    }

}