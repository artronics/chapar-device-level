package it.unibo.sdwn.controller.artronics;

import it.unibo.sdwn.controller.BaseController;
import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.trasport.Transport;
import org.springframework.beans.factory.annotation.Autowired;

public class SdwnController extends BaseController implements Controller
{
    @Autowired
    public SdwnController(Transport transportImpl)
    {
        super(transportImpl);
    }

    @Override
    public void init()
    {
        super.init();
    }
}
