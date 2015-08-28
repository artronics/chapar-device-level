package artronics.chapar.node;

public interface Quality extends Comparable<Quality>
{
    int getQualityValue();

    void setQualityValue(int value);
}
