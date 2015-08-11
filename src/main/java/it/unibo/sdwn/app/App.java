package it.unibo.sdwn.app;

import it.unibo.sdwn.config.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App
{
    public Logger log(Log logType){
        Logger logger = LoggerFactory.getLogger(logType.toString());

        return logger;
    }
}
