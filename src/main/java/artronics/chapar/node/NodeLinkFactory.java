package artronics.chapar.node;

final class NodeLinkFactory
{
    public static Link create(Node sourceNode, Node destinationNode, Quality linkQuality)
    {
        return new NodeLink(sourceNode, destinationNode, linkQuality);
    }
}
