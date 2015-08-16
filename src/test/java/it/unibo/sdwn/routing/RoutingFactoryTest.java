package it.unibo.sdwn.routing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:DependencyInjection.xml")
public class RoutingFactoryTest
{
    @Test
    public void It_should_give_a_singleton_considering_dependency_injection()
    {
        Routing routing = new RoutingFactory().getInstance();
        assertNotNull(routing);
    }

}