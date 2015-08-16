package it.unibo.sdwn.map;

import it.unibo.sdwn.map.artronics.SdwnMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DependencyInjection.xml")
public class NetworkMapFactoryTest
{
    //If you changed default implementation of below fields
    //in DependencyInjection.xml, you have to also change
    //these fields to your new implementations.
    private static NetworkMap networkMapImpl = new SdwnMap();

    @Test
    public void It_should_give_a_singleton()
    {
        NetworkMap map = new NetworkMapFactory().getInstance();
        NetworkMap secondMap = new NetworkMapFactory().getInstance();

        assertSame(map, secondMap);
    }

    @Test
    public void It_should_resolve_its_dependendencies_considering_DI()
    {
        NetworkMap map = new NetworkMapFactory().getInstance();

        assertNotNull(map);
        assertEquals(networkMapImpl.getClass(), map.getClass());
    }
}