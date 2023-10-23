package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class $wakeup {

    private static Selector selector;

    @Test
    public void test1() throws IOException {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    selector.wakeup();
                    System.out.println("wakeup() --->" + System.currentTimeMillis());
                    Set<SelectionKey> set1 = selector.keys();
                    Set<SelectionKey> set2 = selector.selectedKeys();
                    System.out.println("执行wakeup()方法之后的 selector的信息：");
                    System.out.println("set1.size()=" + set1.size());
                    System.out.println("set2.size()=" + set2.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel1.configureBlocking(false);

        selector = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);

        long start = System.currentTimeMillis();
        System.out.println("select() start...");
        int keyCount = selector.select();
        System.out.println("select() end..." + (System.currentTimeMillis() - start));

        Set<SelectionKey> selectedKeysSet = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectedKeysSet.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isAcceptable()) {
                ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                Socket socket = channel.socket().accept();
                socket.close();
            }
            iterator.remove();
        }
        serverSocketChannel1.close();
        System.out.println("main end!");
    }
}
