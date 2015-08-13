package it.unibo.sdwn.Packet;

import it.unibo.sdwn.node.BaseNode;

/**
 * Created by jalal on 05/08/15.
 */
public class Response extends Packet{

    private Payload data;
    private byte responseBytes[];

    public Response(BaseNode destinationNode, Payload data) {
        this.data = data;

        byte[] response = new byte[10 + (data.length)];
        System.arraycopy(data, 0, response, 10, data.length);

        this.responseBytes = response;
    }

    @Override
    public int getLength() {
        return this.responseBytes.length;
    }

    @Override
    public byte[] getPacketBytes() {
        return responseBytes;
    }
}
