package artronics.chapar.core.analyser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

        String act = ToCsv.create(56, 64, args, 78, strArgs);
        assertEquals(exp, act);
    }

    @Test
    public void Check_enums()
    {
        String act = ToCsv.create(foo.bar);
        String exp = "bar            ;;";
        assertEquals(exp, act);
    }

    @Test
    public void test_passing_a_format()
    {
        String exp = "12  ;foo ;";
        String act = ToCsv.createStrCsv(4, "12", "foo");

        assertEquals(exp, act);
    }

}

enum foo
{
    bar,
    baz
}
