package artronics.chapar.node;

import artronics.chapar.map.BaseNetworkMap;
import artronics.chapar.map.NetworkMap;

import java.util.List;

public class BaseNode<A> implements Node<A>
{

    private final A address;

    public BaseNode(A address)
    {
        this.address = address;
    }

    @Override
    public A getAddress()
    {
        return address;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Node) {
            Node node = (Node) obj;
            return address.equals(node.getAddress());
        }

        return false;
    }
}
