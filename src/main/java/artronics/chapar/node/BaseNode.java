package artronics.chapar.node;

import artronics.chapar.map.BaseNetworkMap;
import artronics.chapar.map.NetworkMap;

import java.util.List;

public class BaseNode<A> implements Node
{

    private final A address;

    public BaseNode(A address)
    {
        this.address = address;
    }
}
