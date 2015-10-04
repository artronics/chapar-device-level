package artronics.chapar.core.analyser;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CsvParserTest
{
    private static final List<List<Integer>> simpleLine = Arrays.asList(Arrays.asList(12,
                                                                                      234,
                                                                                      0,
                                                                                      0));
    private static final List<List<Integer>> twoSecs = Arrays.asList(Arrays.asList(78, 12345),
                                                                     Arrays.asList(34, 4));

    private static final List<List<Integer>> withoutSec = Arrays.asList(Arrays.asList(23, 4, 55));
    private List<List<Integer>> expValues = new ArrayList<>();
    private List<List<Integer>> actValues;

    private CsvParser parser;
    private List<String> lines = new ArrayList<>();

    @Before
    public void setUp() throws Exception
    {
        lines.add("12;  234; 0 ;	 00;;;;");
        lines.add(" 78; 12345 ;; 34 ; 4 ;;;;");
        lines.add("23;  4 ; 55 ;;");

        parser = new CsvParser(lines);
    }


    @Test
    public void test_simple_csv_line()
    {
        actValues = parser.getValuesTable().get(0);
        expValues = simpleLine;

        assertEquals(expValues, actValues);
    }

    @Test
    public void test_with_two_sections_in_one_records()
    {
        actValues = parser.getValuesTable().get(1);
        expValues = twoSecs;

        assertEquals(expValues, actValues);
    }

    @Test
    public void test_a_record_without_sec()
    {
        actValues = parser.getValuesTable().get(2);
        expValues = withoutSec;

        assertEquals(expValues, actValues);

    }
}