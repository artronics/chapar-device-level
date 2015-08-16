package it.unibo.sdwn.app.event;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class EventTest
{
    @Test
    public void It_should_be_a_singleton()
    {
        EventBus first = Event.mainBus();
        EventBus second = Event.mainBus();

        assertSame(first, second);
    }

}