/*
package it.unibo.sdwn.Packet;

import it.unibo.sdwn.helper.UnsignedByte;

import java.util.ArrayList;

public class Payload
{

    private ArrayList<UnsignedByte> data;
    public int length = 0;

    public Payload(int size) {
        this.data = new ArrayList();
        this.length = size;
        init();
    }

    public ArrayList<UnsignedByte> getData() {
        return data;
    }

    public Payload init(){
        //place your desired init method here
        this.initDummy();
        return this;
    }
    public void initDummy(){
        for (data ){
            data[i] = new UnsignedByte(i);
        }
    }
    public void init(UnsignedByte initValue){
        for (int i=0; i<data.length; ++i){
            data[i] = initValue;
        }
    }

    public void init(UnsignedByte[] initValues){
        if (initValues.length != data.length)
            throw new IndexOutOfBoundsException("Data and initializer array must have the same size");
        for (int i=0; i<data.length; ++i){
            data[i] = initValues[i];
        }
    }

    public UnsignedByte getByte(int index) {
        return data[index];
    }

    public void setByte(int index, UnsignedByte value) {
        data[index] = value;
    }
}
*/