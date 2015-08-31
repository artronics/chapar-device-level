package artronics.chapar.core;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.controller.BaseController;
import artronics.chapar.controller.ControllerService;
import artronics.chapar.protocol.ProtocolEngineService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

final class ChaparServiceInitializer
{
    private ConnectionService connection;
    private ProtocolEngineService protocolEngine;
    private ControllerService controller;

    public void setController(Class<? extends BaseController> controller)
    {
//        this.controller = controller;
    }

    public void start()
    {
        connection.establishConnection();
        connection.open();
    }

    public void setConnection(ConnectionService connection)
    {
        this.connection = connection;
    }

    public void setProtocolEngine(ProtocolEngineService protocolEngine)
    {
        this.protocolEngine = protocolEngine;
    }
}
