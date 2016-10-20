package Client.network;



import Client.messages.SerializedMessage;

import java.io.Serializable;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Created by Matteo on 11/10/2016.
 */
public class Peer{

    private InetAddress addr;
    private int port;
    private Queue<SerializedMessage> pendingMessages;
    private SerializedMessage incompleteMsg;
    private PeerState state;

    public Peer(InetAddress addr,int port){
        pendingMessages = new LinkedList<>();
        this.addr = addr;
        this.port = port;
        state = PeerState.HANDSAKE;
        incompleteMsg = null;
    }

    public SerializedMessage getMsg() {
        return incompleteMsg;
    }


    public void setMsg(SerializedMessage msg) {
        this.incompleteMsg = msg;
    }


    public PeerState getState() {
        return state;
    }

    public void setPeerState(PeerState state) {
        this.state = state;
    }


    public InetAddress getAddress() {
        return addr;
    }

    public int getPort() {
        return port;
    }

    public  synchronized void addMsg(SerializedMessage message) {
        pendingMessages.add(message);
    }

    public SerializedMessage peekMsg() {
        return pendingMessages.peek();
    }

    public SerializedMessage poolMsg() {
        return pendingMessages.poll();
    }

    public boolean hasNoPendingMessage() {
        return pendingMessages.isEmpty();
    }
}