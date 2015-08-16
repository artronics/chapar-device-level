package it.unibo.sdwn.routing;

import it.unibo.sdwn.map.NetworkMap;
import it.unibo.sdwn.map.SdwnMap;
import it.unibo.sdwn.routing.artronics.SdwnRouting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DependencyInjection.xml")
public class RoutingFactoryTest
{
    //If you changed default implementation of below fields
    //in DependencyInjection.xml, you have to also change
    //these fields to your new implementations.
    private static NetworkMap networkMapImpl = new SdwnMap();
    private static Routing routingImpl = new SdwnRouting();

    @Test
    public void It_should_give_a_singleton()
    {
        Routing routing = new RoutingFactory().getInstance();
        Routing secondRouting = new RoutingFactory().getInstance();

        assertSame(routing, secondRouting);
    }

    @Test
    public void It_should_resolve_its_dependendencies_considering_DI()
    {
        Routing routing = new RoutingFactory().getInstance();
        NetworkMap networkMap = routing.getNetworkMap();

        assertNotNull(routing);
        assertNotNull(networkMap);

        assertEquals(routingImpl.getClass(), routing.getClass());
        assertEquals(networkMapImpl.getClass(), networkMap.getClass());
    }
}