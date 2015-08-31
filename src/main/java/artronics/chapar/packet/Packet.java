package artronics.chapar.packet;

public interface Packet
{
    enum Type
    {
        DATA(0),
        MALFORMED(1),
        BEACON(2),
        REPORT(3),
        RULE_REQUEST(4),
        RULE_RESPONSE(5),
        OPEN_PATH(6);

        private int value;

        Type(int value)
        {
            if (value > 6)
                this.value = 6;
            else
                this.value = value;
        }

        public int getValue()
        {
            return value;
        }
    }

    enum Direction
    {
        IN,
        OUT
    }
}
