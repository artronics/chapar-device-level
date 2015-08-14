package it.unibo.sdwn.app.config;

import it.unibo.sdwn.controller.Controller;
import it.unibo.sdwn.controller.artronics.SdwnController;
import it.unibo.sdwn.map.NetworkMap;
import it.unibo.sdwn.map.SdwnMap;
import it.unibo.sdwn.routing.Routing;
import it.unibo.sdwn.routing.artronics.SdwnRouting;
import it.unibo.sdwn.trasport.ComTransport;
import it.unibo.sdwn.trasport.Transport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan(value = {
        "it.unibo.sdwn.controller.artronics",
        "it.unibo.sdwn.routing.artronics",
        "it.unibo.sdwn.map",
        "it.unibo.sdwn.transport"
})
public class DependencyInjection
{
    @Bean
    public Controller getController()
    {
        return new SdwnController();
    }

    @Bean
    public Routing getRouting()
    {
        return new SdwnRouting();
    }

    @Bean
    public NetworkMap getNetworkMap()
    {
        return new SdwnMap();
    }

    @Bean
    public Transport getTransport()
    {
        return new ComTransport();
    }
}
