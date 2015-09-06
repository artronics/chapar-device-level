package artronics.chapar.core.logger;

import artronics.chapar.core.analyser.Analysable;
import artronics.chapar.core.analyser.CsvLoggerWrapper;
import org.apache.log4j.Logger;

public interface Log
{
    Logger MAIN = Logger.getLogger("main");
    Logger PACKET = Logger.getLogger("packet");
    Logger EVENT = Logger.getLogger("event");
    Logger CSV = Logger.getLogger("csv");


    static Logger main()
    {
        return MAIN;
    }

    static Logger event()
    {
        return EVENT;
    }

    static Logger packet()
    {
        return PACKET;
    }

    @Deprecated
    static void csv(Analysable analysable, String loggerName)
    {
        CsvLoggerWrapper.log(loggerName, analysable);
    }

    @Deprecated
    static void csv(Analysable analysable)
    {
        String logname = analysable.getClass().getSimpleName();
        CsvLoggerWrapper.log(logname, analysable);
    }

    static void csv(String loggerName, Analysable csv)
    {
        CsvLoggerWrapper.log(loggerName, csv);
    }

    @Deprecated
    static void csv(String loggerName, String csv)
    {
        CsvLoggerWrapper.log(loggerName, csv);
    }
}
