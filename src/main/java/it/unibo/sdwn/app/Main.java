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
        long startTime = System.currentTimeMillis();

        byte total = 0;
        int initialCapacity = 1000000;
//        ArrayList<UnsignedByte> b = new ArrayList<>(initialCapacity);
//        UnsignedByte[] b= new UnsignedByte[initialCapacity];
//        for (int i = 0; i < initialCapacity; i++) {
////            b.add(UnsignedByte.of(0));
//            b[i]=UnsignedByte.of(0);
//        }
//        byte[] bb = new byte[initialCapacity] ;
//        for (int i = 0; i < initialCapacity; i++) {
//            bb[i]=0;
//        }
//
//        long stopTime = System.currentTimeMillis();
//        long elapsedTime = stopTime - startTime;
//        System.out.println(elapsedTime);
    }
}
