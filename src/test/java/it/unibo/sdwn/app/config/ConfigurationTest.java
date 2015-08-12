package it.unibo.sdwn.app.config;

import junit.framework.TestCase;


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