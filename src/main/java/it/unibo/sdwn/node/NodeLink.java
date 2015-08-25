package it.unibo.sdwn.node;

public final class NodeLink implements Link
{
    private static final LinkType linkType = LinkType.FULL_DUPLEX;
    private Quality quality;
    private Node destinationode;

    public NodeLink(Node destinationNode)
    {
        this.destinationode = destinationNode;
    }

    public NodeLink(Node destinationNode, Quality linkQuality)
    {
        this.destinationode = destinationNode;
        this.quality = linkQuality;
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
