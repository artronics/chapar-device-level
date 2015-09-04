package artronics.chapar.node;

interface Link
{
    int getWeigth();

    void setWeigth(int weigth);

    Node getSource();

    Node getTarget();

    boolean hasNode(Node node);
}
