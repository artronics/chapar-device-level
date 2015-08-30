package artronics.chapar.core;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.protocol.ProtocolEngineService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapar
{
    private ConnectionService connection;
    private ProtocolEngineService protocolEngine;

    public void start()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("chapar_DI.xml");
        connection = context.getBean(ConnectionService.class);
        protocolEngine = context.getBean(ProtocolEngineService.class);

        connection.establishConnection();
        connection.open();

        protocolEngine.start();


    }
}
