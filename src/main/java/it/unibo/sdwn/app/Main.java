package it.unibo.sdwn.app;

import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.trasport.events.TransportEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance();
        app.init(args);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "DependencyInjection.xml");

        Event.mainBus().post(new TransportEvent());
        String key = Config.get().getString("KEY");
        System.out.println(key);
    }
}
