package artronics.chapar.node;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QualityTest
{
    @Test
    public void Test_static_factory(){
        Quality l = LinkQualityFactory.create(10);
        assertEquals(l.getClass(), LinkQuality.class);
    }
}