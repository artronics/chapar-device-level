package it.unibo.sdwn.app;

import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.controller.artronics.SdwnController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DependencyInjection.xml")
public class AppTest
{
    //If you changed default implementation of below fields
    //in DependencyInjection.xml, you have to also change
    //these fields to your new implementations.
    private static Controller controllerImpl = new SdwnController();

    @Test
    public void It_should_give_a_singleton()
    {
        App app = App.getInstance();
        App secondApp = App.getInstance();

        assertNotNull(app);
        assertSame(app, secondApp);
    }

    @Test
    public void It_should_resolve_its_dependendencies_considering_DI()
    {
        App app = App.getInstance();
        Controller controller = app.getController();

        assertNotNull(controller);
        assertEquals(controllerImpl.getClass(), controller.getClass());
    }
}