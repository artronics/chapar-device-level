package artronics.chapar.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Log
{
    Logger MAIN = LoggerFactory.getLogger("main");

    static Logger main()
    {
        return MAIN;
    }
}
