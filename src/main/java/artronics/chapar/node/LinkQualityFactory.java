package artronics.chapar.node;

public class LinkQualityFactory
{
    public static final int MAX_VALUE = 255;
    public static final LinkQuality MAX_QUALITY = new LinkQuality(MAX_VALUE);

    static Quality create(int qualityValue)
    {
        return new LinkQuality(qualityValue);
    }

    static Quality createMaxQuality(){
        return MAX_QUALITY;
    }
}
