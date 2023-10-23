package nio.socket_server_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;

public class Test22 {
    @Test
    public void test1() throws IOException {
        long beginTime = 0;
        long endTime = 0;
        SocketChannel socketChannel = SocketChannel.open();
        // SocketChannel是非阻塞模式
        socketChannel.configureBlocking(false);
        // connect()方法的返回值表示如果建立了连接，则为true
        // 如果此通道处于非阻塞模式且连接操作正在进行中，则为 false
        boolean connectResult = socketChannel.connect(new InetSocketAddress("localhost",
            8088));
        if (connectResult == false) {
            System.out.println("connectResult == false");
            while (!socketChannel.finishConnect()) {
                System.out.println("一直在尝试连接");
            }
        }
        socketChannel.close();
    }

    @Test
    public void test2() throws IOException {
        long beginTime = 0;
        long endTime = 0;
        SocketChannel socketChannel = SocketChannel.open();
        // SocketChannel是非阻塞模式
        socketChannel.configureBlocking(false);
        boolean connectResult = socketChannel.connect(new InetSocketAddress("localhost", 8088));
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                    ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
                    serverSocketChannel1.bind(new InetSocketAddress("localhost", 8088));
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    socketChannel.close();
                    serverSocketChannel1.close();
                    System.out.println("server end!");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        if (connectResult == false) {
            System.out.println("connectResult == false");
            while (!socketChannel.finishConnect()) {
                System.out.println("一直在尝试连接");
            }
            System.out.println("已建立上连接");
        }
        socketChannel.close();
    }
}
