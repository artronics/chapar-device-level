package artronics.chapar.core.analyser;

import java.util.List;

public interface Analyser<T>
{
    void addRecord(List<T> record);
}
