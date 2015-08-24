package it.unibo.sdwn.app.analyser;

public interface Analysable
{
    default String toCsv()
    {
        return toString();
    }
}
