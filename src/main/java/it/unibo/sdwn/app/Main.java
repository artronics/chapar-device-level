package it.unibo.sdwn.app;

import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.routing.RoutingFactory;
import it.unibo.sdwn.trasport.events.TransportIsReady;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance();
        app.init(args);

        Event.mainBus().post(new TransportIsReady());
        Routing routing = new RoutingFactory().getInstance();
        System.out.println(routing);

    }
}
