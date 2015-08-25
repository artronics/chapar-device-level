package it.unibo.sdwn.node;

import java.util.ArrayList;

public abstract class AbstractBaseNode implements Node
{
    private final AbstractBaseAddress address;
    private final Node.Type type;
    private ArrayList<Link> links;

    protected AbstractBaseNode(AbstractBaseAddress address)
    {
        this.type = Type.NORMAL;
        this.address = address;
    }

    protected AbstractBaseNode(AbstractBaseAddress address, Node.Type type)
    {
        this.type = type;
        this.address = address;
    }
}
