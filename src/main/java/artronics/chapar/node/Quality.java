package artronics.chapar.node;

interface Quality extends Comparable<Quality>
{
    int getQualityValue();

    void setQualityValue(int value);
}
