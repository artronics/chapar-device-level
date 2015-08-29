package artronics.chapar.node;

public interface NodeFactory
{
    Node create(int address, NodeType type);
    Node createNode(int address);
    Node createSink(int address);
}
