package artronics.chapar.node;

interface Link
{
    Quality getQuality();

    void setQuality(Quality quality);

    Node getNode1();

    Node getNode2();

    boolean hasNode(Node node);
}
