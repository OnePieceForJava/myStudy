package nio.socket_server_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

import org.junit.Test;

public class $bind_backlog {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8888), 60);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888), 60);
        Thread.sleep(5000);
        boolean isRun = true;
        int i = 0;
        while (isRun == true) {
            System.out.println("start");
            Socket socket = serverSocket.accept();
            System.out.println("end");
            System.out.println((++i) + "--->" + socket.isConnected());
            //socket.close();
        }
        Thread.sleep(8000);
        serverSocket.close();
        serverSocketChannel.close();
    }

    @Test
    public void clent() throws IOException {
        for (int i = 0; i < 100; i++) {
            Socket socket = new Socket("127.0.0.1", 8888);
            System.out.println(socket.isConnected());
            //socket.close();
            System.out.println("客户端连接个数为：" + (i + 1));
        }
    }
}
