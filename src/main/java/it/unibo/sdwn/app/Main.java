package it.unibo.sdwn.app;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance(args);
        app.init();
    }
}
