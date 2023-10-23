package nio.socket_server_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

import org.junit.Test;

public class Test18 {

    @Test
    public void test1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel.configureBlocking(false);

        Selector selector1 = Selector.open();
        Selector selector2 = Selector.open();

        SelectionKey selectionKey1 = serverSocketChannel.register(selector1, SelectionKey.OP_ACCEPT);
        System.out.println("selectionKey1=" + selectionKey1.hashCode());

        SelectionKey selectionKey2 = serverSocketChannel.register(selector2, SelectionKey.OP_ACCEPT);
        System.out.println("selectionKey2=" + selectionKey2.hashCode());
        serverSocketChannel.close();
    }

    @Test
    public void test2() throws IOException {
        ServerSocketChannel serverSocketChannel1 = null;
        serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = null;
        serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.configureBlocking(false);

        Selector selector = Selector.open();

        SelectionKey key1 = serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey key2 = serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println(key1);
        System.out.println(key2);

        serverSocketChannel1.close();
        serverSocketChannel2.close();
    }

    @Test
    public void test3() throws IOException {
        ServerSocketChannel serverSocketChannel1 = null;
        serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = null;
        serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.configureBlocking(false);

        Selector selector1 = Selector.open();
        Selector selector2 = Selector.open();

        SelectionKey key1 = serverSocketChannel1.register(selector1, SelectionKey.
            OP_ACCEPT);
        SelectionKey key2 = serverSocketChannel2.register(selector2, SelectionKey.
            OP_ACCEPT);

        System.out.println(key1);
        System.out.println(key2);

        serverSocketChannel1.close();
        serverSocketChannel2.close();
    }

    @Test
    public void test4() throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        SelectionKey key1 = serverSocketChannel.register(selector, SelectionKey.
            OP_ACCEPT);
        SelectionKey key2 = serverSocketChannel.register(selector, SelectionKey.
            OP_ACCEPT);

        System.out.println(key1);
        System.out.println(key2);

        serverSocketChannel.close();
    }

}
