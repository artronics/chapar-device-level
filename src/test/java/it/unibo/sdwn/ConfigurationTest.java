package it.unibo.sdwn;

import it.unibo.sdwn.app.config.Configuration;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConfigurationTest extends TestCase
{
    public void testGetAppConfig()
    {
        Configuration conf = new Configuration();
        String actual = conf.getAppConfig().getProperty("testKey");
        String expected = "testValue";
        assertEquals(expected, actual);
    }
}