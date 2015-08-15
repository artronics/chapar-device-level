package it.unibo.sdwn.app;

import it.unibo.sdwn.app.event.Event;
import it.unibo.sdwn.trasport.events.TransportIsReady;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance(args);
        app.init();

        Event.mainBus().post(new TransportIsReady());
    }
}
