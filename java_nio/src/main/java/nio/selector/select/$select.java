package nio.selector.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * public abstract int select() 方法的作用是选择一组键，其相应的通道已为I/O操作准备就绪。
 * 此方法执行处于阻塞模式的选择操作。
 * 仅在至少选择一个通道、调用此选择器的wakeup（）方法，
 * 或者当前的线程已中断（以先到者为准）后，此方法才返回。
 * 返回值代表添加到就绪操作集的键的数目，该数目可能为零，为零代表就绪操作集中的内容并没有添加新的键，
 * 保持内容不变。
 */
public class $select {

    /**
     * Selector.select()方法是一个阻塞方法
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println("1");
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 8888), 50);
        System.out.println("2");
        serverSocketChannel.configureBlocking(false);
        System.out.println("3");
        Selector selector = Selector.open();
        System.out.println("4");
        SelectionKey selectionKey1 = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("5");
        //代码将在这阻塞，selectionKey1的操作集为准备就绪，操作集为OP_ACCEPT
        int keyCount = selector.select();
        System.out.println("6 keyCount=" + keyCount);
        serverSocketChannel.close();
        System.out.println("7 end!");
    }

    /**
     * 客户端
     */
    @Test
    public void client() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        socket.close();
    }

    /**
     * select()方法不阻塞，出现死循环
     * <p>
     * 步骤1：单独运行test3(),无输出，程序阻塞先运行。
     * 步骤2：运行client(),有输出，程序一直在运行。出现“死循环”。
     * <p>
     * 原因：
     * 在客户端连接服务端时，服务端中的通道对accept事件并未处理，导致accept事件一直存在，
     * 也就是select（）方法一直检测到有准备好的通道要对accept事件进行处理，但一直未处理，就一直
     * 呈“死循环”输出的状态了。解决“死循环”的办法是将accept事件消化处理。
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel.configureBlocking(false);
        Selector selector1 = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel.register(selector1, SelectionKey.OP_ACCEPT);
        boolean isRun = true;
        while (isRun == true) {
            int keyCount = selector1.select();
            Set<SelectionKey> set1 = selector1.keys();
            Set<SelectionKey> set2 = selector1.selectedKeys();
            System.out.println("keyCount =" + keyCount);
            System.out.println("set1 size=" + set1.size());
            System.out.println("set2 size=" + set2.size());
            System.out.println();
        }
        serverSocketChannel.close();
    }

    /**
     * 解决死循环方案
     * <p>
     * test3()与test4()的区别就是对accept事件是否处理?
     *
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel.configureBlocking(false);
        Selector selector1 = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel.register(selector1, SelectionKey.OP_ACCEPT);
        boolean isRun = true;
        while (isRun == true) {
            int keyCount = selector1.select();
            Set<SelectionKey> set1 = selector1.keys();
            Set<SelectionKey> set2 = selector1.selectedKeys();
            System.out.println("keyCount =" + keyCount);
            System.out.println("set1 size=" + set1.size());
            System.out.println("set2 size=" + set2.size());
            System.out.println();

            Iterator<SelectionKey> iterator = set2.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                System.out.println(serverSocketChannel.hashCode());
                System.out.println(channel.hashCode());
                channel.accept();// 使用方法accept()将事件处理掉
            }
        }
        serverSocketChannel.close();
    }

}
