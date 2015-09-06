package artronics.chapar.core.analyser;

import artronics.chapar.core.logger.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ToCsv
{
    //European countries use ";" as
    //CSV separator because "," is their digit separator
    public static final String CSV_SEPARATOR = ";";
    public static final int INT_WIDTH = 4;
    public static final int STR_WIDTH = 15;
    private final List intList = new ArrayList<>();

    public static void write(String fileName, String csv)
    {
        Log.csv(fileName, csv);
    }

    public static void write(String fileName, Analysable csv)
    {
        Log.csv(fileName, csv.toCsv());
    }

    public static String creat(Object... args)
    {
        String csv = "";
        for (Object arg : args) {
            if (arg instanceof Collection<?>) {
                Collection<?> subArgs = (Collection<?>) arg;
                Iterator it = subArgs.iterator();
                while (it.hasNext()) {
                    csv += formater(it.next());
                    csv += CSV_SEPARATOR;
                }
                continue;
            }

            csv += formater(arg);
            csv += CSV_SEPARATOR;
        }
        csv += CSV_SEPARATOR;
        return csv;
    }

    private static void writeCsv(String csv)
    {
        System.out.println(csv);
    }

    private static String formatString(String arg, int width)
    {
        String format = "%-" + width + "s";
        return String.format(format, arg);
    }

    private static String formater(Object arg)
    {
        if (arg instanceof Number) {
            Number number = (Number) arg;
            return formatString(number.toString(), INT_WIDTH);
        }

        return formatString(arg.toString(), STR_WIDTH);
    }
}
