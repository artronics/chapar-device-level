package it.unibo.sdwn.app;

import com.google.common.eventbus.EventBus;
import it.unibo.sdwn.app.config.Config;
import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.trasport.events.TransportEvent;

public class Main
{
    public static void main(String[] args)
    {
        App app = new App();
        app.initCommandLine(args);
        app.initEventBus();


        EventBus eventBus = Event.mainBus();
        eventBus.post(new TransportEvent());
        String key = Config.get().getString("KEY");
        System.out.println(key);
    }
}
