package it.unibo.sdwn.controller;

import it.unibo.sdwn.controller.artronics.SdwnController;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.routing.artronics.SdwnRouting;
import it.unibo.sdwn.trasport.ComTransport;
import it.unibo.sdwn.trasport.Transport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DependencyInjection.xml")
public class ControllerFactoryTest
{
    //If you changed default implementation of below fields
    //in DependencyInjection.xml, you have to also change
    //these fields to your new implementations.
    private static Routing routingImpl = new SdwnRouting();
    private static Controller controllerImpl = new SdwnController();
    private static Transport transportImpl = new ComTransport();

    @Test
    public void It_should_give_a_singleton()
    {
        Controller controller = new ControllerFactory().getInstance();
        Controller secondController = new ControllerFactory().getInstance();

        assertSame(controller, secondController);
    }

    @Test
    public void It_should_resolve_its_dependendencies_considering_DI()
    {
        Controller controller = new ControllerFactory().getInstance();
        Routing routing = controller.getRouting();
        Transport transport = controller.getTransport();

        assertNotNull(routing);
        assertNotNull(transport);

        assertEquals(controllerImpl.getClass(), controller.getClass());
        assertEquals(routingImpl.getClass(), routing.getClass());
        assertEquals(transportImpl.getClass(), transport.getClass());
    }
}