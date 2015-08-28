package artronics.chapar.node;

import com.sun.istack.internal.NotNull;

public final class NodeLink implements Link
{
    //Since the link is reciprocal there
    //is no difference between sourceNode and
    //destinationNode there are simply two ends
    //of a relationship
    private final Node sourceNode;
    private final Node destinationNode;
    private Quality quality;

    protected NodeLink(@NotNull Node sourceNode, @NotNull Node destinationNode, @NotNull Quality quality)
    {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.quality = quality;
    }

    @Override
    public boolean hasNode(Node node)
    {
        return node.equals(destinationNode) || node.equals(sourceNode);
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

    @Override
    public Node getSourceNode()
    {
        return sourceNode;
    }

    @Override
    public Node getDestinationNode()
    {
        return destinationNode;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Link))
            return false;
        Link otherLink = (Link) o;
        //if two links has diff quality they are not equal
        if (!quality.equals(otherLink.getQuality()))
            return false;

        //there is no difference between source and dest
        //nodes. they must be equals one to another.
        Node s = sourceNode;
        Node d = destinationNode;
        Node od = otherLink.getDestinationNode();
        Node os = otherLink.getSourceNode();
        if (!(
                //It must hold the same reference OR Same address
                (s.equals(os) && d.equals(od)) || (s.equals(od) && d.equals(os)) ||
                (s == os && d == od) || (s == od && d == os)
        ))
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        int result = 21;
        result = result * 31 + destinationNode.hashCode() + sourceNode.hashCode();
        result = result * 31 + quality.hashCode();

        return result;
    }
}
