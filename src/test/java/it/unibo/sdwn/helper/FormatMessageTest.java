package it.unibo.sdwn.helper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FormatMessageTest
{
    private List someList;
    private Object[] someArray;

    @Before
    public void setUp()
    {
        someList = new ArrayList<Object>();
        someList.add("first option");
        someList.add("second option");

        someArray = new Object[]{"first option", "second option"};
    }

    @Test
    public void It_format_a_enumeration_as_a_bullet_list()
    {
        String expectedMsg = new String("[1]\tfirst option\n[2]\tsecond option\n");
        String msg = FormatMessage.MakeChoiceableString(someList);

        assertEquals(expectedMsg, msg);
    }

    @Test
    public void It_should_place_a_header_string()
    {
        String header = "Some information on header:";
        String expectedMsg = new String(header + "\n" + "[1]\tfirst option\n[2]\tsecond option\n");
        String msg = FormatMessage.MakeChoiceableString(someList, header);

        assertEquals(expectedMsg, msg);
    }

    @Test
    public void It_should_also_accept_array()
    {
        String expectedMsg = new String("[1]\tfirst option\n[2]\tsecond option\n");
        String msg = FormatMessage.MakeChoiceableString(someArray);

        assertEquals(expectedMsg, msg);
    }

    @Test
    public void It_should_place_a_header_string_when_pass_array()
    {
        String header = "Some information on header:";
        String expectedMsg = new String(header + "\n" + "[1]\tfirst option\n[2]\tsecond option\n");
        String msg = FormatMessage.MakeChoiceableString(someArray, header);

        assertEquals(expectedMsg, msg);
    }

}