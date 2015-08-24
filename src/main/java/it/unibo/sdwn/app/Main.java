package it.unibo.sdwn.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("DependencyInjection.xml");
        App app = context.getBean(App.class);
        app.initCommandLine(args);
        app.initEventBus();
        app.start();

    }
}
