package it.unibo.sdwn.trasport;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransportFactory
{
    private static Transport transport;

    public synchronized Transport getInstance()
    {
        if (transport == null) {
            //get dependencies
            ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext(
                    "DependencyInjection.xml");
            transport = xmlContext.getBean(Transport.class);

            return transport;
        }

        return transport;
    }
}
