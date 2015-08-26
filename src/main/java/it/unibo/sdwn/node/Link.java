package it.unibo.sdwn.node;

public interface Link
{
    Quality getQuality();

    void setQuality(Quality quality);

    Node getSourceNode();

    Node getDestinationNode();

    boolean hasNode(Node node);
}
