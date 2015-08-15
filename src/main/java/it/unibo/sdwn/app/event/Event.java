package it.unibo.sdwn.app.event;

import com.google.common.eventbus.EventBus;

public class Event
{
    private static EventBus mainBus;
    private static boolean isInstanciated = false;

    private Event()
    {
    }

    public static EventBus mainBus()
    {
        if (!isInstanciated) {
            mainBus = new EventBus();
            isInstanciated = true;
            return mainBus;
        }

        return mainBus;
    }

}
