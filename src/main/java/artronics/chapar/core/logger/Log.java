package artronics.chapar.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Log
{
    Logger MAIN = LoggerFactory.getLogger("main");
    Logger PACKET = LoggerFactory.getLogger("packet");

    static Logger main()
    {
        return MAIN;
    }

    static Logger packet()
    {
        return PACKET;
    }
}
