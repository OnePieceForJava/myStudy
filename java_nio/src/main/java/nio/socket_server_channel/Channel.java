package nio.socket_server_channel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

import org.junit.Test;

public class Channel {

    /**
     * 通过查看上述的示例代码可以发现:
     * 在不使用ServerSocketChannel类，而只是单纯地使用ServerSocket类与Socket类，也能实现服务端与客户端通信的目的，
     * 那么为什么要使用ServerSocketChannel通道呢？
     * 因为单纯地使用ServerSocket类与Socket类，而不使用ServerSocketChannel类是实现不了I/O多路复用的。
     *
     * @throws IOException
     */
    @Test
    public void test_server1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //serverSocketChannel.bind(new InetSocketAddress("127.0.0.1",8888));
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", 8888));

        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] charArray = new char[1024];
        int readLength = inputStreamReader.read(charArray);
        while (readLength != -1) {
            String newString = new String(charArray, 0, readLength);
            System.out.println(newString);
            readLength = inputStreamReader.read(charArray);
        }
        inputStreamReader.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        serverSocketChannel.close();
    }

}
