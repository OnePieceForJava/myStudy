package nio.selector;

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

public class $5_8_8 {

    @Test
    public void test1() throws InterruptedException, IOException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 7777));
        serverSocketChannel1.configureBlocking(false);

        Selector selector1 = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel1.register(selector1, SelectionKey.OP_ACCEPT);

        while (true) {
            int keyCount = selector1.select();
            Set<SelectionKey> set1 = selector1.keys();
            Set<SelectionKey> set2 = selector1.selectedKeys();

            System.out.println("keyCountA =" + keyCount);
            System.out.println("set1 size=" + set1.size());
            System.out.println("set2 size=" + set2.size());
            System.out.println();

            Iterator<SelectionKey> iterator = set2.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);

                SelectionKey key2 = socketChannel.register(selector1, SelectionKey.OP_READ);
                System.out.println("key2.isReadable()=" + ((SelectionKey.OP_READ & ~key2.interestOps()) == 0));
                System.out.println("key2.isWritable()=" + ((SelectionKey.OP_WRITE & ~key2.interestOps()) == 0));
                System.out.println("keyCountB =" + selector1.select());
                System.out.println("set1 size=" + set1.size());
                System.out.println("set2 size=" + set2.size());
                System.out.println("key == key2的结果：" + (key == key2));
                System.out.println();

                SelectionKey key3 = socketChannel.register(selector1, SelectionKey.
                    OP_READ | SelectionKey.OP_WRITE);
                System.out.println("key3.isReadable()=" + ((SelectionKey.OP_READ & ~key3.interestOps()) == 0));
                System.out.println("key3.isWritable()=" + ((SelectionKey.OP_WRITE & ~key3.interestOps()) == 0));

                System.out.println("keyCountB =" + selector1.select());
                System.out.println("set1 size=" + set1.size());
                System.out.println("set2 size=" + set2.size());
                System.out.println("key2==key3结果：" + (key2 == key3));
            }
            Thread.sleep(Integer.MAX_VALUE);
        }
        //serverSocketChannel1.close();
    }

    @Test
    public void test2() throws IOException {
        Socket socket7777 = new Socket("localhost", 7777);
        socket7777.getOutputStream().write("12345".getBytes());
        socket7777.close();
    }

}
