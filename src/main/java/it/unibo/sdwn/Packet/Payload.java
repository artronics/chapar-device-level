package it.unibo.sdwn.Packet;

public class Payload implements ByteStream
{

    private byte data[];
    public int length = 0;

    public Payload(int sizeInBytes) {
        this.data = new byte[sizeInBytes];
        this.length = sizeInBytes;
    }

    public byte[] getData() {
        return data;
    }

    public Payload init(){
        //place your desired init method here
        this.initDummy();
        return this;
    }
    public void initDummy(){
        for (int i=0; i<data.length; ++i){
            data[i] = (byte)i;
        }
    }
    public void init(byte initValue){
        for (int i=0; i<data.length; ++i){
            data[i] = initValue;
        }
    }

    public void init(byte[] initValues){
        if (initValues.length != data.length)
            throw new IndexOutOfBoundsException("Data and initializer array must have the same size");
        for (int i=0; i<data.length; ++i){
            data[i] = initValues[i];
        }
    }

    public byte getByte(int index) {
        return data[index];
    }

    public void setByte(int index, byte value) {
        data[index] = value;
    }
}
