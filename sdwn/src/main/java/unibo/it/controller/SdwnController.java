package unibo.it.controller;

import artronics.chapar.controller.BaseController;
import artronics.chapar.node.Node;
import artronics.chapar.packet.Packet;

import java.util.ArrayList;

public class SdwnController extends BaseController
{
    public void kk(){
        Node node = nodeFactory.createNode(4);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(78);
        data.add(78);
        data.add(78);
        data.add(78);
        Packet packet = packetFactory.create(Packet.Type.DATA, Packet.Direction.IN,new ArrayList<>());

    }
}
