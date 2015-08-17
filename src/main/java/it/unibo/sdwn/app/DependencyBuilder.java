package it.unibo.sdwn.app;

import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.map.NetworkMap;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.trasport.Transport;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class DependencyBuilder
{
    private Controller controller;

    public void start()
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "DependencyInjection.xml");

        Controller controllerImpl = context.getBean(Controller.class);
        Transport transportImpl = context.getBean(Transport.class);
        Routing routingImpl = context.getBean(Routing.class);
        NetworkMap networkMapImpl = context.getBean(NetworkMap.class);

        controllerImpl.setTransport(transportImpl);
        controllerImpl.setRouting(routingImpl);

        routingImpl.setNetworkMap(networkMapImpl);

        context.close();

        this.controller = controllerImpl;
    }

    public Controller getController()
    {
        return this.controller;
    }
}
