package artronics.chapar.node;

public class LinkQualityFactory
{
    public static final int MAX_VALUE = 255;
    public static final LinkWeigth MAX_QUALITY = new LinkWeigth(MAX_VALUE);

    static Weigth create(int qualityValue)
    {
        return new LinkWeigth(qualityValue);
    }

    static Weigth createMaxQuality()
    {
        return MAX_QUALITY;
    }
}
