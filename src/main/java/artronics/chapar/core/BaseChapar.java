package artronics.chapar.core;

import artronics.chapar.broker.PacketsInOut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class BaseChapar
{
    protected PacketsInOut packetsIn;

    public static void start()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapar_DI.xml");
        ChaparServiceInitializer initializer = context.getBean(ChaparServiceInitializer.class);
        initializer.start();
    }

    public void setPacketsIn(PacketsInOut packetsIn)
    {
        this.packetsIn = packetsIn;
    }
}
