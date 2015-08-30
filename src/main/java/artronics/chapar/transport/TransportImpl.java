package artronics.chapar.transport;

import artronics.chapar.connection.Connection;
import artronics.chapar.queue.DataInOutQueueContract;

import java.util.concurrent.ArrayBlockingQueue;

public class TransportImpl implements Transport
{
    private final ArrayBlockingQueue<byte[]> inQueue;
    private final ArrayBlockingQueue<byte[]> outQueue;
    private final Connection connection;
    private boolean isClosed = true;

    public TransportImpl(DataInOutQueueContract inOutQueue, Connection connection)
    {
        this.connection = connection;
        this.inQueue = inOutQueue.getDataInQueue();
        this.outQueue = inOutQueue.getDataOutQueue();

        this.connection.establishConnection();
        this.connection.open();
    }

    @Override
    public void start()
    {

    }

    @Override
    public void close()
    {
        isClosed = true;
        connection.close();
    }

    private class TransportRecevier implements Runnable
    {
        @Override
        public void run()
        {
            while (!isClosed) {

            }
        }
    }

}
