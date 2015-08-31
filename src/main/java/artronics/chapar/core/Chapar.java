package artronics.chapar.core;

import artronics.chapar.connection.ConnectionService;
import artronics.chapar.controller.BaseController;
import artronics.chapar.protocol.ProtocolEngineService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

final public class Chapar
{
    private Chapar()
    {
    }
    public static void start(){
        ApplicationContext context = new ClassPathXmlApplicationContext("chapar_DI.xml");
        ChaparServiceInitializer initializer = context.getBean(ChaparServiceInitializer.class);
        initializer.start();


    }
    public static void setController(Class<? extends BaseController> controller){

    }
}
