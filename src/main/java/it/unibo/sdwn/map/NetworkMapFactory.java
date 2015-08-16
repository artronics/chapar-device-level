package it.unibo.sdwn.map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NetworkMapFactory
{
    private static NetworkMap networkMap;

    public synchronized NetworkMap getInstance()
    {
        if (networkMap == null) {
            //get dependencies
            ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext(
                    "DependencyInjection.xml");
            networkMap = xmlContext.getBean(NetworkMap.class);

            xmlContext.close();

            return networkMap;
        }

        return networkMap;
    }
}
