package artronics.chapar.core.logger;

import artronics.chapar.core.analyser.Analysable;
import artronics.chapar.core.analyser.CsvLoggerWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Log
{
    Logger MAIN = LoggerFactory.getLogger("main");
    Logger PACKET = LoggerFactory.getLogger("packet");
    Logger CSV = LoggerFactory.getLogger("csv");

    static Logger main()
    {
        return MAIN;
    }

    static Logger packet()
    {
        return PACKET;
    }

    static void csv(Analysable analysable, String loggerName)
    {
        CsvLoggerWrapper.log(loggerName, analysable);
    }

    static void csv(Analysable analysable)
    {
        String logname = analysable.getClass().getSimpleName();
        CsvLoggerWrapper.log(logname, analysable);
    }
}
