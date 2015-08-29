package artronics.chapar.node;

public interface LinkQualityFactory
{
    int MAX_VALUE = 255;
    LinkQuality MAX_QUALITY = new LinkQuality(MAX_VALUE);

    static Quality create(int qualityValue)
    {
        return new LinkQuality(qualityValue);
    }

    static Quality createMaxQuality(){
        return MAX_QUALITY;
    }
}
