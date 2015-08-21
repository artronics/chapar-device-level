package it.unibo.sdwn.app;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/DependencyInjection.xml")
public class DependencyTreeTest
{
    /*
        This test will test your interface implementations
        for those which you resolved with DI
        If you change any implementations, consider changing
        @Before setUp method to indicate new changes.

        Also remember if you change Dependency tree you
        have to change according tests methods.
     */
    /*private String controllerImpl;
    private String transportImpl;
    private String routingImpl;
    private String networkMapImpl;

    @Before
    public void setUp()
    {
        this.controllerImpl = SdwnController.class.getSimpleName();
        this.transportImpl = ComTransport.class.getSimpleName();
        this.routingImpl = SdwnRouting.class.getSimpleName();
        this.networkMapImpl = SdwnMap.class.getSimpleName();

    }

    @Test
    public void It_should_load_correct_implementation_of_dependencies()
    {
////        String[] args = {"someArgs"};
////        App app = App.getInstance();
////        app.init(args);
//
//        //get dependency tree
//        Controller controller = app.getController();
//        Transport transport = controller.getTransport();
//        Routing routing = controller.getRouting();
//        NetworkMap networkMap = routing.getNetworkMap();
//
//        assertEquals(controller.getClass().getSimpleName(), controllerImpl);
//        assertEquals(transport.getClass().getSimpleName(), transportImpl);
//        assertEquals(routing.getClass().getSimpleName(), routingImpl);
//        assertEquals(networkMap.getClass().getSimpleName(), networkMapImpl);

    }
*/
}