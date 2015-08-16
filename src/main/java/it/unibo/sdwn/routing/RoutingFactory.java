package it.unibo.sdwn.routing;

import it.unibo.sdwn.map.NetworkMap;
import it.unibo.sdwn.map.NetworkMapFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RoutingFactory
{
    private static NetworkMap networkMap;
    private static Routing routing;

    public synchronized Routing getInstance()
    {
        if (routing == null) {
            //get dependencies
            ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext(
                    "DependencyInjection.xml");
            networkMap = new NetworkMapFactory().getInstance();
            routing = xmlContext.getBean(Routing.class);
            xmlContext.close();

            routing.setNetworkMap(networkMap);
            return routing;
        }

        return routing;
    }
}
