package nio.socket_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;

public class $isConnectionPending {
    @Test
    public void server() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 8088));
        SocketChannel socketChannel = serverSocketChannel1.accept();
        socketChannel.close();
        serverSocketChannel1.close();
        System.out.println("server end!");
    }

    /**
     * （1）阻塞通道(通道未绑定地址)，IP不存在
     */
    @Test
    public void test1() {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            System.out.println("socketChannel未绑定地址-->"+socketChannel.isConnectionPending());
            // 192.168.0.123此IP不存在
            socketChannel.connect(new InetSocketAddress("255.255.255.255", 8088));
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IP地址不存在 -->" + socketChannel.isConnectionPending());
        }
    }

    public void test2(){

    }
}