package artronics.chapar.core.events;

import com.google.common.eventbus.EventBus;

public class Event
{
    private static final EventBus MAIN_BUS = new EventBus();
    private static final EventBus GUI_BUS = new EventBus();

    protected Event()
    {
    }

    public static EventBus mainBus()
    {
        return MAIN_BUS;
    }

    public static EventBus guiBus()
    {
        return GUI_BUS;
    }
}
