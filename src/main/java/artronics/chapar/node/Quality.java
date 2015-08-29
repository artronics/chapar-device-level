package artronics.chapar.node;

interface Quality extends Comparable<Quality>
{
    static Quality create(int qualityValue)
    {
        return new LinkQuality(qualityValue);
    }
    int getQualityValue();

    void setQualityValue(int value);
}
