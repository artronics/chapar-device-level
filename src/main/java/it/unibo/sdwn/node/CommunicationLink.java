package it.unibo.sdwn.node;

public final class CommunicationLink implements Link
{
    private static final LinkType linkType = LinkType.FULL_DUPLEX;
    private Quality quality;
    private INode destinationode;

    public CommunicationLink(INode destinationNode)
    {
        this.destinationode = destinationNode;
    }

    public CommunicationLink(INode destinationNode, Quality linkQuality)
    {
        this.destinationode = destinationNode;
        this.quality = linkQuality;
    }

    @Override
    public INode getDestinationNode()
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
