package it.unibo.sdwn.node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LinkQualityTest
{
    private Quality eqQuality;
    private Quality eqQuality1;
    private Quality notEqQuality;

    @Before
    public void setUp() throws Exception
    {

        eqQuality = new LinkQuality(10);
        eqQuality1 = new LinkQuality(10);
        notEqQuality = new LinkQuality(20);

    }

    @Test
    public void Test_equality()
    {
        assertEquals(eqQuality, eqQuality1);
        assertEquals(eqQuality.getQualityValue(), eqQuality1.getQualityValue());
        //Not equals
        assertNotEquals(eqQuality, notEqQuality);
    }

    @Test
    public void It_should_return_same_hash_code_for_equal_qualities()
    {
        assertEquals(eqQuality.hashCode(), eqQuality1.hashCode());
        //and not the same for not equal quality
        assertNotEquals(eqQuality.hashCode(), notEqQuality.hashCode());
    }

    @Test
    public void Test_comparison()
    {
        Quality q = new LinkQuality(10);
        Quality q1 = new LinkQuality(15);

        assertEquals(q.compareTo(q1), -1); // <
        assertEquals(q1.compareTo(q1), 0); // =
        assertEquals(q1.compareTo(q), 1);  // <
    }

}