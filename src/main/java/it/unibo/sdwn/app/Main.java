package it.unibo.sdwn.app;

import it.unibo.sdwn.config.Configuration;
import it.unibo.sdwn.config.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main
{
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        Configuration con = new Configuration();

        App app = new App();
        app.log(Log.main).debug("foooo main o");
        app.log(Log.stdout).debug("stdout fooooo");

    }
}
