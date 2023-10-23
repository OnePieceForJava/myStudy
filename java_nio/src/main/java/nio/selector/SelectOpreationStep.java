package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

/**
 * SelectionKey.cancel()方法后
 * 改键 会加入到Selector的已取消键集中
 * 验证SelectionKey对应的通道是否会关闭
 */
public class SelectOpreationStep {
    @Test
    public void test1() throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT);

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
        socketChannel.connect(inetSocketAddress);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    System.out.println("after -->" + selector.keys().size());
                    System.out.println("socketChannel.isOpen()-->" + socketChannel.isOpen());
                    Thread.sleep(5000);
                    selector.wakeup();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();
        Thread.sleep(5000);

        boolean isRun = true;
        while (isRun) {
            int selectCount = selector.select();
            System.out.println("selectCount = " + selectCount);
            Set<SelectionKey> keys = selector.keys();
            System.out.println("keys.size()-->" + keys.size());

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            System.out.println("selectedKeys.size()-->" + selectedKeys.size());
            System.out.println("socketChannel.isOpen()-->" + socketChannel.isOpen());

            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                key.cancel();
                System.out.println("key.isValid()-->" + key.isValid());
                countDownLatch.countDown();
                iterator.remove();
            }
        }

    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        boolean isRun = true;
        while (isRun) {
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();

                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = channel.accept();
                }
                iterator.remove();
            }
        }
    }
}
