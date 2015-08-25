package it.unibo.sdwn.node;

public class LinkQuality implements Quality
{
    private int qualityValue;

    public LinkQuality(int qualityValue)
    {
        this.qualityValue = qualityValue;
    }

    @Override
    public int getQualityValue()
    {
        return this.qualityValue;
    }

    @Override
    public void setQualityValue(int qualityValue)
    {
        this.qualityValue = qualityValue;

    }
}
