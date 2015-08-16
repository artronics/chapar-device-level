package it.unibo.sdwn.app.config;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ConfigTest
{
    @Test
    public void It_should_be_a_singleton()
    {
        PropertiesConfiguration first = Config.get();
        PropertiesConfiguration second = Config.get();

        assertSame(first, second);
    }

}