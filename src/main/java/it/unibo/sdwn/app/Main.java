package it.unibo.sdwn.app;

import it.unibo.sdwn.node.Address;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance(args);
        app.init();

    }
}
