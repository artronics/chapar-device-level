package artronics.chapar.core;

import artronics.chapar.broker.MessageBroker;
import artronics.chapar.broker.PacketsInOut;
import artronics.chapar.connection.ConnectionService;
import artronics.chapar.packet.PacketFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

final public class Chapar
{
    private final BaseChapar chapar = new BaseChapar();

    public Chapar()
    {
    }

    public static void start(String xml)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapar_DI.xml", xml);
        ChaparServiceInitializer initializer = context.getBean(ChaparServiceInitializer.class);

        initializer.start();
    }

    public static void start()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"chapar_DI.xml"});
        ChaparServiceInitializer initializer = context.getBean(ChaparServiceInitializer.class);

        initializer.start();
    }
}
