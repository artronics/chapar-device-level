package it.unibo.sdwn.controller;

import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.routing.RoutingFactory;
import it.unibo.sdwn.trasport.Transport;
import it.unibo.sdwn.trasport.TransportFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerFactory
{
    private static Controller controller;
    private static Transport transport;
    private static Routing routing;

    public synchronized Controller getInstance()
    {
        if (controller == null) {
            //get dependencies
            ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext(
                    "DependencyInjection.xml");
            controller = xmlContext.getBean(Controller.class);
            transport = new TransportFactory().getInstance();
            routing = new RoutingFactory().getInstance();
            xmlContext.close();

            return controller;
        }

        return controller;
    }

}
