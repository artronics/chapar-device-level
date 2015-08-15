package it.unibo.sdwn.app.event;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.App;
import it.unibo.sdwn.app.logger.Log;

public class RegisterHandler
{
    /*
        Here you can register singletons or static classes
        You can also register any instance of a class by
        invoking Event.<busName>().register()
     */
    public static void registerAll()
    {
        App app = new App();
        Event.mainBus().register(new RegisterHandler());
        Event.mainBus().register(app);
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
