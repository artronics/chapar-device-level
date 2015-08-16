package it.unibo.sdwn.trasport;

import it.unibo.sdwn.trasport.artronics.ComTransport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DependencyInjection.xml")
public class TransportFactoryTest
{
    //If you changed default implementation of below fields
    //in DependencyInjection.xml, you have to also change
    //these fields to your new implementations.
    private static Transport transportImpl = new ComTransport();

    @Test
    public void It_should_give_a_singleton()
    {
        Transport first = new TransportFactory().getInstance();
        Transport second = new TransportFactory().getInstance();

        assertSame(first, second);
    }

    @Test
    public void It_should_resolve_its_dependendencies_considering_DI()
    {
        Transport transport = new TransportFactory().getInstance();

        assertNotNull(transport);
        assertEquals(transportImpl.getClass(), transport.getClass());
    }
}