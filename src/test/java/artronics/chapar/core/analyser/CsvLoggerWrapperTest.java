package artronics.chapar.core.analyser;

import org.junit.Test;

import static org.junit.Assert.*;

public class CsvLoggerWrapperTest
{
    @Test
    public void Test_read_config_file()
    {

        String[] foo = {"packets", "network_map"};
        CsvLoggerWrapper csv = new CsvLoggerWrapper();
//        assertArrayEquals(foo,csv.csvFiles);
    }

    @Test
    public void It_shoud_log_to_csv()
    {
//        CsvLoggerWrapper.log("packets", new Analysable()
//        {
//            @Override
//            public String toCsv()
//            {
//                return "foo kir";
//            }
//        });
    }

}