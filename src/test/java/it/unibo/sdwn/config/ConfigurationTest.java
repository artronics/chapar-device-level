package it.unibo.sdwn.config;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConfigurationTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ConfigurationTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(ConfigurationTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue(true);
    }

    public void getAppConfigTest()
    {
        Configuration conf = new Configuration();
        String actual = conf.getAppConfig().getProperty("testKey");
        String expected = "testValue";
        //Assert.assertEquals(expected,actual);
        assertEquals(expected, actual);
    }
}