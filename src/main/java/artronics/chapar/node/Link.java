package artronics.chapar.node;

interface Link
{
    Quality getQuality();

    void setQuality(Quality quality);

    Node getSourceNode();

    Node getDestinationNode();

    boolean hasNode(Node node);
}
