package nio.selector.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class RepeatSelect {

    /**
     * 重复消费问题
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.socket().bind(new InetSocketAddress("localhost", 7777));
        serverSocketChannel1.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.socket().bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel2.configureBlocking(false);

        Selector selector1 = Selector.open();

        SelectionKey selectionKey7777 = serverSocketChannel1.register(selector1, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey8888 = serverSocketChannel2.register(selector1, SelectionKey.OP_ACCEPT);

        boolean isRun = true;
        while (isRun == true) {

            int keyCount = selector1.select();
            System.out.println("------------------------------------------");
            Set<SelectionKey> keys = selector1.keys();
            Set<SelectionKey> selectedKeys = selector1.selectedKeys();

            System.out.println("keyCount =" + keyCount);
            System.out.println("键集大小：" + keys.size());
            System.out.println("已选择键集大小：" + selectedKeys.size());
            System.out.println();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                InetSocketAddress socketAddress = (InetSocketAddress)channel.getLocalAddress();
                System.out.println("端口：" + socketAddress.getPort() + " 被客户端连接了！");
                SocketChannel socketChannel = channel.accept();
                if (socketChannel == null) {
                    System.out.println("socketChannel == null");
                }
                System.out.println();
            }
        }
        serverSocketChannel1.close();
        serverSocketChannel2.close();
    }

    /**
     * 解决重复消费问题
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.socket().bind(new InetSocketAddress("localhost", 7777));
        serverSocketChannel1.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.socket().bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel2.configureBlocking(false);

        Selector selector1 = Selector.open();

        SelectionKey selectionKey7777 = serverSocketChannel1.register(selector1, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey8888 = serverSocketChannel2.register(selector1, SelectionKey.OP_ACCEPT);

        boolean isRun = true;
        while (isRun == true) {

            int keyCount = selector1.select();
            System.out.println("------------------------------------------");
            Set<SelectionKey> keys = selector1.keys();
            Set<SelectionKey> selectedKeys = selector1.selectedKeys();

            System.out.println("keyCount =" + keyCount);
            System.out.println("键集大小：" + keys.size());
            System.out.println("已选择键集大小：" + selectedKeys.size());
            System.out.println();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                InetSocketAddress socketAddress = (InetSocketAddress)channel.getLocalAddress();
                System.out.println("端口：" + socketAddress.getPort() + " 被客户端连接了！");
                SocketChannel socketChannel = channel.accept();
                if (socketChannel == null) {
                    System.out.println("socketChannel == null");
                }
                System.out.println();
            }
        }
        serverSocketChannel1.close();
        serverSocketChannel2.close();
    }

    @Test
    public void client777() throws IOException {
        Socket socket = new Socket("localhost", 7777);
        socket.close();
    }

    @Test
    public void client888() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        socket.close();
    }
}
