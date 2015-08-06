package it.unibo.sdwn.app;

import it.unibo.sdwn.config.Configuration;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        Configuration con = new Configuration();
        System.out.println(con.getAppConfig().getProperty("comPort"));
        con.getAppConfig().setProperty("kjcomPort", "jalal");
    }
}
