package it.unibo.sdwn.app.event;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.logger.Log;

/*
    Use this class for general purpose event handlers
    and registering specif classes which there is no
    other appropriate place for them.
 */
public class RegisterHandler
{
    /*
        Here you can register singletons or static classes
        You can also register any instance of a class by
        invoking Event.<busName>().register()
     */
    public static void registerAll()
    {
        Event.mainBus().register(new RegisterHandler());
    }

    /*
        Here is a general event handler. If you need to hook into
        every event. place your logic here.
     */

    @Subscribe
    public void generalHandler(AppBaseEvent e)
    {
        Log.event().info("{} just occurred.", e.getClass().getSimpleName());
    }
}
