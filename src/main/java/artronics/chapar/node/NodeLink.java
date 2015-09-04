package artronics.chapar.node;

import com.sun.istack.internal.NotNull;

final public class NodeLink extends AbstractLink
{

    protected NodeLink(@NotNull Node source, @NotNull Node target, @NotNull int weight)
    {
        super(source, target, weight);
    }
}
