package it.unibo.sdwn.app.config;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigTest
{
    //if this test failed. try to copy App.config from
    //source dir to test directory
    //these two files must be identical to make this
    //test pass.
    @Test
    public void It_should_return_value_for_given_key(){
        String actual = Config.get(ConfigType.KEY);
        String expected = "value";

        assertEquals(expected,actual);
    }

    @Test
    public void It_should_give_int_value_by_parsing(){
        String actualStr = Config.get(ConfigType.INT_KEY);
        int actual = Integer.parseInt(actualStr) ;
        int expected = 10;

        assertEquals(expected,actual);
    }
}