package nio.socket_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;

public class $connect {

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 8088));
        SocketChannel socketChannel = serverSocketChannel1.accept();
        socketChannel.close();
        serverSocketChannel1.close();
        System.out.println("server end!");
    }


    //单独运行会在connect方法处抛出异常
    @Test
    public void blockingClient() throws IOException {
        long beginTime = 0;
        long endTime = 0;
        boolean connectResult = false;
        try {
            // SocketChannel是阻塞模式
            // 在发生错误或连接到目标之前，connect()方法一直是阻塞的
            SocketChannel socketChannel = SocketChannel.open();
            //socketChannel.configureBlocking(true);
            beginTime = System.currentTimeMillis();
            System.out.println("isBlocking->" + socketChannel.isBlocking());
            connectResult = socketChannel.connect(new InetSocketAddress("localhost", 8088));
            endTime = System.currentTimeMillis();
            System.out.println("正常连接耗时：" + (endTime - beginTime) + " connectResult=" + connectResult);
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
            endTime = System.currentTimeMillis();
            System.out.println("异常连接耗时：" + (endTime - beginTime) + " connectResult=" + connectResult);
        }
    }



    @Test
    public void nonBlockingClient() throws IOException, InterruptedException {
        long beginTime = 0;
        long endTime = 0;
        SocketChannel socketChannel = SocketChannel.open();
        // SocketChannel是非阻塞模式
        socketChannel.configureBlocking(false);
        beginTime = System.currentTimeMillis();
        System.out.println("isBlocking->" + socketChannel.isBlocking());
        boolean connectResult = socketChannel.connect(new InetSocketAddress("localhost", 8088));
        //System.out.println("boolean connectResult-->" + connectResult);
        endTime = System.currentTimeMillis();
        System.out.println("连接耗时：" + (endTime - beginTime) + " connectResult=" + connectResult);
        //Thread.sleep(10000);
        while (!socketChannel.isConnected()) {
            //System.out.println(socketChannel.isConnected());
        }
        System.out.println(System.currentTimeMillis() - beginTime);
        socketChannel.close();
    }
}