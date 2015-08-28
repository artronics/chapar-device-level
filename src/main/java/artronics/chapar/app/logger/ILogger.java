package artronics.chapar.app.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ILogger
{
    //for each enum entry create a method:
    public static Logger main()
    {

        Logger logger = LoggerFactory.getLogger(LogType.main.toString());

        return logger;

    }

    public static Logger stdout()
    {

        Logger logger = LoggerFactory.getLogger(LogType.stdout.toString());

        return logger;

    }

    public static Logger packet()
    {

        Logger logger = LoggerFactory.getLogger(LogType.packet.toString());

        return logger;

    }

    public static Logger event()
    {

        Logger logger = LoggerFactory.getLogger(LogType.event.toString());

        return logger;

    }

    enum LogType
    {
        main, stdout, packet, event
    }
}
