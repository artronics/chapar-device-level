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

    @Deprecated
    public static void write(String fileName, Analysable csv)
    {
        Log.csv(fileName, csv.toCsv());
    }

    public static void write(String fileName, String csv)
    {
        Log.csv(fileName, csv);
    }

    public static String createStrCsv(int strWidth, String... args)
    {
        String csv = "";
        for (Object arg : args) {
            if (arg instanceof Collection<?>) {
                Collection<?> subArgs = (Collection<?>) arg;
                Iterator it = subArgs.iterator();
                while (it.hasNext()) {
                    csv += formater(it.next(), INT_WIDTH, strWidth);
                    csv += CSV_SEPARATOR;
                }
                continue;
            }

            csv += formater(arg, INT_WIDTH, strWidth);
            csv += CSV_SEPARATOR;
        }

        return csv;
    }

    public static String create(Object... args)
    {
        String csv = "";
        for (Object arg : args) {
            if (arg instanceof Collection<?>) {
                Collection<?> subArgs = (Collection<?>) arg;
                Iterator it = subArgs.iterator();
                while (it.hasNext()) {
                    csv += formater(it.next(), INT_WIDTH, STR_WIDTH);
                    csv += CSV_SEPARATOR;
                }
                continue;
            }

            csv += formater(arg, INT_WIDTH, STR_WIDTH);
            csv += CSV_SEPARATOR;
        }
        csv += CSV_SEPARATOR;
        return csv;
    }

    public static String appendSeparator(String csv)
    {
        return csv + CSV_SEPARATOR;
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

    private static String formater(Object arg, int intWidth, int strWidth)
    {
        if (arg instanceof Number) {
            Number number = (Number) arg;
            return formatString(number.toString(), intWidth);
        }

        return formatString(arg.toString(), strWidth);
    }
}
