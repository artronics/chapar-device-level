package artronics.chapar.node;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkWeigthTest
{

    private Weigth eqWeigth;
    private Weigth eqWeigth1;
    private Weigth notEqWeigth;

    @Before
    public void setUp() throws Exception
    {
        eqWeigth = new LinkWeigth(10);
        eqWeigth1 = new LinkWeigth(10);
        notEqWeigth = new LinkWeigth(20);
    }

    @Test
    public void Test_equality()
    {
        assertNotEquals(eqWeigth, null);
        //reflexive
        assertEquals(eqWeigth, eqWeigth);

        assertEquals(eqWeigth, eqWeigth1);
        assertEquals(eqWeigth.getQualityValue(), eqWeigth1.getQualityValue());
        //Not equals
        assertNotEquals(eqWeigth, notEqWeigth);
    }

    @Test
    public void It_should_return_same_hash_code_for_equal_qualities()
    {
        assertEquals(eqWeigth.hashCode(), eqWeigth1.hashCode());
        //and not the same for not equal quality
        assertNotEquals(eqWeigth.hashCode(), notEqWeigth.hashCode());
    }

    @Test
    public void Test_comparison()
    {
        Weigth q = new LinkWeigth(10);
        Weigth q1 = new LinkWeigth(15);

        assertEquals(q.compareTo(q1), -1); // <
        assertEquals(q1.compareTo(q1), 0); // =
        assertEquals(q1.compareTo(q), 1);  // <
    }
}