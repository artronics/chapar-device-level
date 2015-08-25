package it.unibo.sdwn.node;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkQualityTest
{
    @Test
    public void Test_equality()
    {
        Quality q = new LinkQuality(10);
        Quality q1 = new LinkQuality(10);
        //I didn't override equals because maybe in
        //future we decided to extend behaviour of
        //this class. Until then use getQualityValue
        //for comparison
        //assertEquals(q,q1);
        assertEquals(q.getQualityValue(), q1.getQualityValue());
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