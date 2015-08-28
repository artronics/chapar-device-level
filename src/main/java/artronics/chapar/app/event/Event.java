package artronics.chapar.app.event;

import com.google.common.eventbus.EventBus;

public class Event
{
    private static EventBus mainBus = new EventBus();

    private Event()
    {
    }

    public static EventBus mainBus()
    {
        return mainBus;
    }

}
