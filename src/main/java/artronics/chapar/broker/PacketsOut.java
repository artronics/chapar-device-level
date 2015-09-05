package artronics.chapar.broker;

public class PacketsOut extends PacketsQueue
{

    private static class PacketsOutLoader
    {
        private static final PacketsOut INSTANCE = new PacketsOut();
    }

    private PacketsOut()
    {
        if (PacketsOutLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static PacketsOut getQueue()
    {
        return PacketsOutLoader.INSTANCE;
    }
}
