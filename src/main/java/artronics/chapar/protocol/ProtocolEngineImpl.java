package artronics.chapar.protocol;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.queue.DataInOutQueueContract;

import java.util.concurrent.ArrayBlockingQueue;

public class ProtocolEngineImpl implements ProtocolEngineService
{
    private final ConnectionService connection;
    private final ArrayBlockingQueue<int[]> dataInQueue;
    private final ArrayBlockingQueue<int[]> dataOutQueue;
    private boolean isClosed = true;

    public ProtocolEngineImpl(ConnectionService connection,
                              DataInOutQueueContract dataQueue)
    {
        this.connection = connection;
        this.dataInQueue = dataQueue.getDataInQueue();
        this.dataOutQueue = dataQueue.getDataOutQueue();
    }

    private class DataInReceiver implements Runnable
    {

        @Override
        public void run()
        {
            while (!isClosed) {
                while (!dataInQueue.isEmpty()) {

                }
            }
        }
    }

    @Override
    public void start()
    {
        connection.establishConnection();
        connection.open();
        isClosed = false;
    }

    @Override
    public void close()
    {
        connection.close();
        isClosed = true;
    }
}
