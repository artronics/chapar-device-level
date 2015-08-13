package it.unibo.sdwn.node;

public final class CommunicationLink implements Link
{
    private static final LinkType linkType = LinkType.FULL_DUPLEX;
    private Quality quality;
    private Node destinationode;

    public CommunicationLink(Node destinationNode)
    {
        this.destinationode = destinationNode;
    }

    @Override
    public Node getDestinationNode()
    {
        return this.destinationode;
    }

    @Override
    public LinkType getLinkType()
    {
        return linkType;
    }

    @Override
    public Quality getQuality()
    {
        return this.quality;
    }

    @Override
    public void setQuality(Quality quality)
    {
        this.quality = quality;
    }
}
