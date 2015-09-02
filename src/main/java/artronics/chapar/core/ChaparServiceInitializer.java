package artronics.chapar.core;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.protocol.ProtocolEngineService;

final class ChaparServiceInitializer
{
    private ConnectionService connection;
    private ProtocolEngineService protocolEngine;

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
