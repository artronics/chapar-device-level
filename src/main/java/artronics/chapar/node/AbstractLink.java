package artronics.chapar.node;

abstract class AbstractLink implements Link
{
    //Since the link is reciprocal there
    //is no difference between source and
    //target there are simply two ends
    //of a relationship
    protected final Node source;
    protected final Node target;
    protected int weight;

    protected AbstractLink(Node source, Node target, int weight)
    {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    @Override
    public boolean hasNode(Node node)
    {
        return node.equals(target) || node.equals(source);
    }

    @Override
    public Node getSource()
    {
        return source;
    }

    @Override
    public Node getTarget()
    {
        return target;
    }

    @Override
    public int getWeigth()
    {
        return this.weight;
    }

    @Override
    public void setWeigth(int weight)
    {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Link))
            return false;
        Link otherLink = (Link) o;
        //if two links has diff weight they are not equal
        if (weight != otherLink.getWeigth())
            return false;

        //there is no difference between source and dest
        //nodes. they must be equals one to another.
        Node s = source;
        Node d = target;
        Node od = otherLink.getTarget();
        Node os = otherLink.getSource();
        return (s.equals(os) && d.equals(od)) || (s.equals(od) && d.equals(os)) ||
                (s == os && d == od) || (s == od && d == os);
    }

    @Override
    public int hashCode()
    {
        int result = 21;
        result = result * 31 + target.hashCode() + source.hashCode();
        result = result * 31 + weight;

        return result;
    }
}
