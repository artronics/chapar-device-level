package artronics.chapar.core;

import artronics.chapar.connection.ConnectionService;

final class ChaparServiceInitializer
{
    private ConnectionService connection;

    public void start()
    {
        connection.establishConnection();
        connection.open();
    }

    public void setConnection(ConnectionService connection)
    {
        this.connection = connection;
    }

    public void stop()
    {
        connection.close();
    }
}
