package artronics.chapar.node;

public interface Link
{
    int getWeigth();

    void setWeigth(int weight);

    Node getSource();

    Node getTarget();

    boolean hasNode(Node node);
}
