package artronics.chapar.routing;

public interface GraphDelegator<N, L, M>
{
    N getShortestPath(N sourceNode, N destinationNode);
}
