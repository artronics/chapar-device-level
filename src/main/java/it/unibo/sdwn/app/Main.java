package it.unibo.sdwn.app;

import it.unibo.sdwn.app.config.Config;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
    public static void main(String[] args)
    {
        App app = App.getInstance();
//        app.init(args);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "DependencyInjection.xml");

        String key = Config.get().getString("KEY");
        System.out.println(key);
    }
}
