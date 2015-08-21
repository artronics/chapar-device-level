package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class Payload
{
    private ArrayList<UnsignedByte> data;

    private int length = 0;

    public Payload(int size)
    {
        this.data = new ArrayList(size);
        this.length = size;
    }
//    public void add()

    public Payload(ArrayList<UnsignedByte> data)
    {
        this.data = data;
    }

    public ArrayList<UnsignedByte> getData()
    {
        return data;
    }

    public Payload init()
    {
        //place your desired init method here
        this.initWithDummyValues();
        return this;
    }

    public void initWithDummyValues()
    {
        for (int i = 0; i < length; i++) {
            data.add(new UnsignedByte(i));
        }
    }

    public void initWithDummyValues(UnsignedByte initValue)
    {
        for (int i = 0; i < length; i++) {
            data.add(initValue);
        }
    }

    public void init(ArrayList<UnsignedByte> arrayList)
    {
        if (arrayList.size() != length)
            throw new IndexOutOfBoundsException("Data and initializer array must have the same size");
        data = new ArrayList<>(arrayList);
    }

    public UnsignedByte getByte(int index)
    {
        return data.get(index);

    }

    public void setByte(int index, UnsignedByte value)
    {
        data.set(index, value);
    }
}
