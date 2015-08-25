package it.unibo.sdwn.node;

public final class NodeLink implements Link
{
    private final Node sourceNode;
    private final Node destinationNode;
    private Quality quality;

    public NodeLink(Node sourceNode, Node destinationNode, Quality quality)
    {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.quality = quality;
    }

    @Override
    public Quality getQuality()
    {
        return this.quality;
    }
}
