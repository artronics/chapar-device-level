package it.unibo.sdwn.helper;

import java.util.List;

public class FormatMessage
{
    public static String MakeChoiceableString(List choiceable)
    {
        return getFormatedString(choiceable);
    }

    public static String MakeChoiceableString(List choiceable, String header)
    {
        String formatedString = getFormatedString(choiceable);
        String msg = header + "\n" + formatedString;
        return msg;
    }

    public static String MakeChoiceableString(Object[] choiceable)
    {
        return getFormatedString(choiceable);
    }

    public static String MakeChoiceableString(Object[] choiceable, String header)
    {
        String formatedString = getFormatedString(choiceable);
        String msg = header + "\n" + formatedString;
        return msg;
    }

    private static String getFormatedString(List choiceable)
    {
        String msg = "";
        for (int i = 0; i < choiceable.size(); ++i) {
            int item = i + 1;
            msg += "[" + item + "]";
            msg += "\t";
            msg += choiceable.get(i).toString();
            msg += "\n";
        }

        return msg;
    }

    private static String getFormatedString(Object[] choiceable)
    {
        String msg = "";
        for (int i = 0; i < choiceable.length; ++i) {
            int item = i + 1;
            msg += "[" + item + "]";
            msg += "\t";
            msg += choiceable[i].toString();
            msg += "\n";
        }

        return msg;
    }
}
