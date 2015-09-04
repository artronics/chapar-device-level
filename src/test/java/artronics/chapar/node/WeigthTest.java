package artronics.chapar.node;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class WeigthTest
{
    @Test
    public void Test_static_factory(){
        Weigth l = LinkQualityFactory.create(10);
        assertEquals(l.getClass(), LinkWeigth.class);
    }
}