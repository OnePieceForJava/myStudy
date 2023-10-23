package nio.socket_server_channel;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class $block_accept {

    //utf-8，汉字战3个字节
    //gbk，汉字战2个字节
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outStream = socket.getOutputStream();
        byte[] bytes = "我是发送的数据Client".getBytes("gbk");
        System.out.println(bytes.length);
        outStream.write(bytes);
        outStream.close();
        socket.close();
    }

    @Test
    public void test1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println(serverSocketChannel.isBlocking());
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        System.out.println("begin " + System.currentTimeMillis());
        SocketChannel socketChannel = serverSocketChannel.accept();//阻塞
        System.out.println("end " + System.currentTimeMillis());
        socketChannel.close();
        serverSocketChannel.close();
    }

    @Test
    public void test2() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println(serverSocketChannel.isBlocking());
        serverSocketChannel.configureBlocking(false);
        System.out.println(serverSocketChannel.isBlocking());
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        System.out.println("begin " + System.currentTimeMillis());
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("  end " + System.currentTimeMillis() + " socketChannel=" +
            socketChannel);
        socketChannel.close();
        serverSocketChannel.close();
    }

    @Test
    public void test3() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        SocketChannel socketChannel = serverSocketChannel.accept();

        ByteBuffer buteBuffer = ByteBuffer.allocate(2);
        int readLength = socketChannel.read(buteBuffer);
        while (readLength != -1) {
            String newString = new String(buteBuffer.array(), "gbk");
            System.out.println(newString);
            buteBuffer.flip();
            readLength = socketChannel.read(buteBuffer);
        }
        socketChannel.close();
        serverSocketChannel.close();
    }
}
