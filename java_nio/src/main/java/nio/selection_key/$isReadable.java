package nio.selection_key;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class $isReadable {

    @Test
    public void server() throws IOException {

        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 8088));
        serverSocketChannel1.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
        SocketChannel socketChannel = null;
        boolean isRun = true;
        while (isRun == true) {
            selector.select();
            Set<SelectionKey> selectedKeysSet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeysSet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    System.out.println("server isAcceptable()");
                    socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    // 对socketChannel注册读的事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    System.out.println("server isReadable()");
                    ByteBuffer buffer = ByteBuffer.allocate(1000);
                    int readLength = socketChannel.read(buffer);
                    while (readLength != -1) {
                        String newString = new String(buffer.array(), 0, readLength);
                        System.out.println(newString);
                        readLength = socketChannel.read(buffer);
                    }
                    socketChannel.close();
                }
                iterator.remove();
            }
        }
        serverSocketChannel1.close();
    }

    @Test
    public void client() {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            SelectionKey selectionKey1 = socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost", 8088));
            int keyCount = selector.select();
            Set<SelectionKey> selectedKeysSet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeysSet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isConnectable()) {
                    // 需要在此处使用finishConnect()方法完成连接，因为socketChannel是非阻塞模式
                    while (!socketChannel.finishConnect()) {
                        System.out.println("! socketChannel.finishConnect()--------");
                    }
                    System.out.println("client isConnectable()");
                    SocketChannel channel = (SocketChannel)key.channel();
                    byte[] writeDate = "我来自客户端，你好，服 务器！".getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(writeDate);
                    channel.write(buffer);
                    channel.close();
                }
            }
            System.out.println("client end !");
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
