package Client.network;

import Client.Main;
import Client.Test;
import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.nio.channels.*;

/**
 * Created by Matteo on 11/10/2016.
 */
public class AcceptTask implements Runnable{

    SocketChannel skt;

    public AcceptTask(SocketChannel skt) {
        this.skt = skt;
    }


    @Override
    public void run() {
        try
        {
            skt.configureBlocking(false);
            Peer peer = new Peer(skt.socket().getInetAddress(),skt.socket().getPort());
            Main.listener.addChannel(skt,SelectionKey.OP_READ,peer);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}