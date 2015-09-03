package artronics.chapar.core.analyser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ToCsvTest
{
    @Test
    public void It_sould_build_a_csv()
    {
        String exp = "56  ;64  ;9   ;3   ;78  ;foo            ;bar            ;;";
        ArrayList<Integer> args = new ArrayList<>();
        args.add(9);
        args.add(3);
        LinkedList<String> strArgs = new LinkedList<>();
        strArgs.add("foo");
        strArgs.add("bar");

        String act = ToCsv.creat(56, 64, args, 78, strArgs);
        assertEquals(exp, act);
    }

    @Test
    public void Check_enums()
    {
        String act = ToCsv.creat(foo.bar);
        String exp = "bar            ;;";
        assertEquals(exp, act);
    }

}

enum foo
{
    bar,
    baz
}
