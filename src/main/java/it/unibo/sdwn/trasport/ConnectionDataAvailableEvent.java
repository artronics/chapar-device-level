package it.unibo.sdwn.trasport;

import it.unibo.sdwn.app.event.AppBaseEvent;

/*
    This event should fire when you get data in connection
    level. Connection level is the lowest layer of Transport
    layer (ie. hardware). For example in case of Serial Comm
    when data is available on buffer you should fire this event.
 */
public class ConnectionDataAvailableEvent extends AppBaseEvent
{
}
