package it.unibo.sdwn.app;

import it.unibo.sdwn.config.Configuration;
import it.unibo.sdwn.config.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{
    public Logger log(Log logType){
        Logger logger = LoggerFactory.getLogger(logType.toString());

        return logger;
    }
    public void init(String[] args){
        this.log(Log.main).debug("foo");
    }
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        Configuration con = new Configuration();
        System.out.println(con.getAppConfig().getProperty("comPort"));
        con.getLogger().debug("kir");


    }
}
