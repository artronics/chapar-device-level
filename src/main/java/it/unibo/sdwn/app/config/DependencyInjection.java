package it.unibo.sdwn.app.config;

import it.unibo.sdwn.node.INode;
import it.unibo.sdwn.node.Node;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.context.annotation.Configuration
@ComponentScan(value = {"it.unibo.sdwn.node"})
public class DependencyInjection
{
    @Bean
    public INode getINode()
    {
        return new Node();
    }
}
