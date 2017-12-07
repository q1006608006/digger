package top.ivan.uav.nio;

import top.ivan.uav.Information;
import top.ivan.uav.Wormhole;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/12/7
 */
public class Sagittarius implements Wormhole {
    private ServerSocket serverSocket;

    private final String address;
    private final int port;

    public Sagittarius(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void init() throws IOException {
        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(address,port));
    }
    @Override
    public Information takeInformation() throws InterruptedException, IOException {
        Socket socket = serverSocket.accept();
        socket.getInputStream();
        return null;
    }

    @Override
    public List<Information> takeInformations() throws InterruptedException, IOException {
        return null;
    }

    @Override
    public boolean sendInformation(Information info) throws IOException {
        return false;
    }
}
