package it.unibo.sdwn.app.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log implements ILogger
{

    public static Logger main()
    {
        return ILogger.main();
    }

    public static Logger stdout()
    {
        return ILogger.stdout();
    }

    public static Logger event()
    {
        return ILogger.event();
    }

    /*
        defualt constructor assume "main" logger as default
     */
    public static Logger Log()
    {
        Logger logger = LoggerFactory.getLogger(LogType.main.toString());

        return logger;
    }

    public static Logger Log(LogType logType)
    {
        Logger logger = LoggerFactory.getLogger(logType.toString());

        return logger;
    }

    public static void instantiate(Object object)
    {
        Log.main().debug("Instanciating: {}", object);
    }

    public static void instantiate(String msg, Object object)
    {
        Log.main().warn(msg + " Instanciating: {}", object);
    }
}
