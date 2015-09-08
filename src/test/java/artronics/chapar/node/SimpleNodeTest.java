package artronics.chapar.node;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class SimpleNodeTest
{
    Node aNode;
    Node sameNode;
    Node otherNode;

    @Before
    public void setUp() throws Exception
    {
        aNode = new SimpleNode(0);
        sameNode = new SimpleNode(0);
        otherNode = new SimpleNode(7);
    }

    @Test
    public void Test_equals(){
        //reflexivity
        assertThat(aNode,equalTo(aNode));

        assertThat(aNode,equalTo(sameNode));
        assertThat(sameNode,equalTo(aNode));

        assertNotEquals(aNode,otherNode);
    }
}