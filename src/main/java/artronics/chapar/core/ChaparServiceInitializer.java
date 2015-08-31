package artronics.chapar.core;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.controller.BaseController;
import artronics.chapar.protocol.ProtocolEngineService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ChaparServiceInitializer
{
    private final ConnectionService connection;
    private final ProtocolEngineService protocolEngine;
    private BaseController controller;

    public ChaparServiceInitializer(ConnectionService connection,
                                    ProtocolEngineService protocolEngine)
    {
        this.connection = connection;
        this.protocolEngine = protocolEngine;
    }

    public void setController(BaseController controller)
    {
        this.controller = controller;
    }

    public void start()
    {
        connection.establishConnection();
        connection.open();
    }

}
