package it.unibo.sdwn.app.event;

import com.google.common.eventbus.EventBus;

public class Event
{
    private static EventBus mainBus = null;

    private Event()
    {
    }

    synchronized public static EventBus mainBus()
    {
        if (mainBus == null) {
            mainBus = new EventBus();
            return mainBus;
        }

        return mainBus;
    }

}
