package artronics.chapar.core.events;

import com.google.common.eventbus.EventBus;

public class Event
{
    private static final EventBus mainBus = new EventBus();

    private Event()
    {
    }

    public static EventBus mainBus()
    {
        return mainBus;
    }
}
