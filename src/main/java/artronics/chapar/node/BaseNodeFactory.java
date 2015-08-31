package artronics.chapar.node;

import artronics.chapar.core.configuration.Config;

final class BaseNodeFactory implements NodeFactory
{
    public static final int SINK_ADDRESS = Config.get().getInt("SINK_ADDRESS");

    public Node create(int address, Node.NodeType type)
    {
        return new BaseNode(address,type);
    }

    @Override
    public Node createNode(int address)
    {
        return new BaseNode(address);
    }

    @Override
    public Node createSink(int address)
    {
        return new BaseNode(SINK_ADDRESS);
    }
}
