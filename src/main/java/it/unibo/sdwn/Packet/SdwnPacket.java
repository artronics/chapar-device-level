package it.unibo.sdwn.Packet;

import it.unibo.sdwn.app.analyser.Analysable;
import it.unibo.sdwn.app.logger.Log;
import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

//Todo it must have a minimum length
public class SdwnPacket implements Analysable
{
    private ArrayList<UnsignedByte> data = new ArrayList<>();
    private Type type;
//    private static long

    public SdwnPacket(ArrayList<UnsignedByte> data)
    {
        if (data == null) {
            String msg = "Null <UnsignedByte>data in SdwnPacket constructor";
            Log.main().error(msg);
            throw new NullPointerException(msg);
        }

        this.data = data;

        UnsignedByte typeByte = this.data.get(byteMeaning.TYPE.value);
        for (Type type : Type.values()) {
            if (typeByte.equals(type.value))
                this.type = type;
        }

        //see original code starter gestisci_pacchetto default
        //case for switch statement. don't forget to change the test
        //See also Type enum constructor
        //TODO What is the default value for Type if it wouldn't be between those values?
        if (typeByte.intValue() > 6)
            this.type = type.OPEN_PATH;
    }

    public Type getType()
    {
        return type;
    }

    @Override
    public String toCsv(Object object)
    {
        return null;
    }

    public boolean is(Type type)
    {
        return (this.type == type);
    }

    public enum Type
    {
        DATA(new UnsignedByte(1)),
        BEACON(new UnsignedByte(2)),
        REPORT(new UnsignedByte(3)),
        RULE_REQUEST(new UnsignedByte(4)),
        RULE_RESPONSE(new UnsignedByte(5)),
        OPEN_PATH(new UnsignedByte(6));

        private UnsignedByte value;

        Type(UnsignedByte value)
        {
            if (value.intValue() > 6)
                this.value = new UnsignedByte(6);
            else
                this.value = value;
        }
    }

    private enum byteMeaning
    {
        LENGTH(0),
        NET_ID(1),
        SOURCE_L(2),
        SOURCE_H(3),
        DESTINATION_L(4),
        DESTINATION_H(5),
        TYPE(6),
        TTL(7),
        NEXT_HOP_L(8),
        NEXT_HOP_H(9),
        DISTANCE(10),
        BATTERY(11),
        NEIGHBOUR(12);

        private int value;

        byteMeaning(int value)
        {
            this.value = value;
        }
    }
}
