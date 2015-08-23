package it.unibo.sdwn.node;

public interface Link
{
    public Node getDestinationNode();

    public LinkType getLinkType();

    ;

    public Quality getQuality();

    public void setQuality(Quality quality);

    public static enum LinkType
    {
        FULL_DUPLEX, HALF_DUPLEX, SIMPLEX
    }

}
