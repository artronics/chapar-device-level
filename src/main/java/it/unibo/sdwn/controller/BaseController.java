package it.unibo.sdwn.controller;

import com.google.common.eventbus.Subscribe;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.trasport.Transport;

public class BaseController implements Runnable
{
    protected static Transport transport;

    public BaseController(Transport transportImpl)
    {
        transport = transportImpl;
    }

    @Subscribe
    public void init()
    {
        Thread controllerThread = new Thread(this);
        Log.main().debug("Controller thread is created and starting...");
        controllerThread.start();
    }

    @Override
    public void run()
    {
        Log.main().debug("inside");
        System.out.println("inside kir");

    }
}
