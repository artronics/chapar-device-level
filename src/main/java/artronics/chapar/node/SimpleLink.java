package artronics.chapar.node;

public class SimpleLink extends AbstractLink
{
    public SimpleLink(Node source, Node target, Integer weight)
    {
        super(source, target, weight);
    }

    @Override
    public String toString()
    {
        String target = String.format("%-4s", getTarget().getAddress().toString());
        String weight = String.format("%-4d", getWeigth());

        return target + ";" + weight;
    }
}
