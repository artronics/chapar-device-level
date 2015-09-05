package artronics.chapar.node;

import com.sun.javafx.collections.SortableList;

public class SimpleLink extends AbstractLink implements Comparable
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

    @Override
    public int compareTo(Object o)
    {
        Link otherLink = (Link) o;

        if (weight > otherLink.getWeigth())
            return 1;

        else if (weight < otherLink.getWeigth())
            return -1;

        else
            return 0;
    }
}
