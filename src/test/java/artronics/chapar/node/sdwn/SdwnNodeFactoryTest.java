package artronics.chapar.node.sdwn;

import artronics.chapar.address.sdwn.SdwnAddress;
import artronics.chapar.node.NodeType;
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
        SdwnNode n = factory.createNode(new SdwnAddress(10));

        assertEquals(n.getType(), NodeType.NORMAL);
        assertNotEquals(n.getType(), NodeType.END_NODE);
    }

    @Test
    public void It_should_create_sink_node()
    {
        SdwnNode n = factory.createSink(new SdwnAddress(0));

        assertEquals(n.getType(), NodeType.SINK);
        assertNotEquals(n.getType(), NodeType.END_NODE);
    }

}