package it.unibo.sdwn.app.analyser;

public interface Analysable<T>
{
    String toCsv(T object);
}
