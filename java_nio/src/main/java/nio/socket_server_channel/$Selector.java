package nio.socket_server_channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class $Selector {
    @Test
    public void test1() throws IOException {
        Selector selector = Selector.open();
        System.out.println(selector);
    }

    @Test
    public void test2() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 必须将ServerSocketChannel设置成非阻塞的
        // 不然会出现：
        // java.nio.channels.IllegalBlockingModeException
        // 异常
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", 8888));

        // 核心代码-开始
        Selector selector = Selector.open();
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 核心代码-结束
        System.out.println("selector=" + selector);
        System.out.println("key=" + key);
        serverSocket.close();
        serverSocketChannel.close();
    }

    @Test
    public void test3() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", 8888));

        System.out.println("A isRegistered=" + serverSocketChannel.isRegistered());

        Selector selector = Selector.open();
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("B isRegistered=" + serverSocketChannel.isRegistered());
        serverSocket.close();
        serverSocketChannel.close();
    }

    @Test
    public void test4() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        Selector selector = Selector.open();
        System.out.println("selector=" + selector);
        System.out.println("A serverSocketChannel1.isRegistered()=" + serverSocketChannel.isRegistered());
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("B serverSocketChannel1.isRegistered()=" + serverSocketChannel.isRegistered());
        serverSocketChannel.close();
    }

    @Test
    public void test5() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));

        System.out.println("A isBlocking=" + serverSocketChannel.isBlocking());
        serverSocketChannel.configureBlocking(false);////// 设置成非阻塞模式
        System.out.println("B isBlocking=" + serverSocketChannel.isBlocking());

        Selector selector = Selector.open();
        System.out.println("selector=" + selector);

        System.out.println("A serverSocketChannel1.isRegistered()=" + serverSocketChannel.isRegistered());
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("B serverSocketChannel1.isRegistered()=" + serverSocketChannel.isRegistered());

        //-->java.nio.channels.IllegalBlockingModeException
        //serverSocketChannel.configureBlocking(true);
        serverSocketChannel.close();
    }

    @Test
    public void test6() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println("A serverSocketChannel.isOpen()=" + serverSocketChannel.isOpen());
        serverSocketChannel.close();
        System.out.println("B serverSocketChannel.isOpen()=" + serverSocketChannel.isOpen());
        serverSocketChannel = ServerSocketChannel.open();
        System.out.println("C serverSocketChannel.isOpen()=" + serverSocketChannel.isOpen());
        serverSocketChannel.close();
    }

    @Test
    public void test7() throws IOException {
        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    Socket socket = new Socket("localhost", 8888);
                    socket.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));

        SocketChannel socketChannel = serverSocketChannel.accept();
        Set<SocketOption<?>> set1 = serverSocketChannel.supportedOptions();
        Set<SocketOption<?>> set2 = socketChannel.supportedOptions();

        Iterator iterator1 = set1.iterator();
        Iterator iterator2 = set2.iterator();

        System.out.println("ServerSocketChannel supportedOptions:");
        while (iterator1.hasNext()) {
            SocketOption each = (SocketOption)iterator1.next();
            System.out.println(each.name() + " " + each.getClass().getName());
        }
        System.out.println();
        System.out.println();
        System.out.println("SocketChannel supportedOptions:");
        while (iterator2.hasNext()) {
            SocketOption each = (SocketOption)iterator2.next();
            System.out.println(each.name() + " " + each.getClass().getName());
        }

        socketChannel.close();
        serverSocketChannel.close();
    }

    @Test
    public void test13() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 通道支持什么，Socket Option就只能设置什么，设置其他的Socket Option就会出现异常
        System.out.println("A SO_RCVBUF=" + serverSocketChannel.getOption(StandardSocketOptions.SO_RCVBUF));
        serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 5678);
        System.out.println("B SO_RCVBUF=" + serverSocketChannel.getOption(StandardSocketOptions.SO_RCVBUF));
        serverSocketChannel.close();
    }

    @Test
    public void test14() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));

        InetSocketAddress address = (InetSocketAddress)serverSocketChannel.getLocalAddress();
        System.out.println(address.getHostString());
        System.out.println(address.getPort());

        serverSocketChannel.close();
    }
}
