package artronics.chapar.node;

import com.sun.istack.internal.NotNull;

class NodeLink implements Link
{
    //Since the link is reciprocal there
    //is no difference between node1 and
    //node2 there are simply two ends
    //of a relationship
    private final Node node1;
    private final Node node2;
    private Quality quality;

    protected NodeLink(@NotNull Node node1, @NotNull Node node2, @NotNull Quality quality)
    {
        this.node1 = node1;
        this.node2 = node2;
        this.quality = quality;
    }

    @Override
    public boolean hasNode(Node node)
    {
        return node.equals(node2) || node.equals(node1);
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
    public Node getNode1()
    {
        return node1;
    }

    @Override
    public Node getNode2()
    {
        return node2;
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
        Node s = node1;
        Node d = node2;
        Node od = otherLink.getNode2();
        Node os = otherLink.getNode1();
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
        result = result * 31 + node2.hashCode() + node1.hashCode();
        result = result * 31 + quality.hashCode();

        return result;
    }
}
