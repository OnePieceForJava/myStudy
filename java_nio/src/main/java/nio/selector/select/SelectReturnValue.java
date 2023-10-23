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

public class SelectReturnValue {

    /**
     * 验证select 返回值的含义
     * <p>
     * 步骤1：运行test6（）
     * 步骤2：运行client777()（）
     * 步骤3：运行client777()（）
     * 步骤4：运行client888_999（）
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void test6() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 7777));
        serverSocketChannel1.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel2.configureBlocking(false);

        ServerSocketChannel serverSocketChannel3 = ServerSocketChannel.open();
        serverSocketChannel3.bind(new InetSocketAddress("localhost", 9999));
        serverSocketChannel3.configureBlocking(false);

        Selector selector1 = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel1.register(selector1, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey2 = serverSocketChannel2.register(selector1, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey3 = serverSocketChannel3.register(selector1, SelectionKey.OP_ACCEPT);

        boolean isRun = true;
        while (isRun == true) {
            int keyCount = selector1.select();
            Set<SelectionKey> set1 = selector1.keys();
            Set<SelectionKey> set2 = selector1.selectedKeys();
            System.out.println("keyCount =" + keyCount);
            System.out.println("键集大小：" + set1.size());
            System.out.println("已选择键集大小：" + set2.size());
            System.out.println();

            Iterator<SelectionKey> iterator = set2.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                serverSocketChannel.accept();
            }
            Thread.sleep(10000);
        }
        serverSocketChannel1.close();
        serverSocketChannel2.close();
        serverSocketChannel3.close();
    }

    @Test
    public void client777() throws IOException {
        Socket socket7777 = new Socket("localhost", 7777);
        socket7777.close();
    }

    @Test
    public void client888_999() throws IOException {
        Socket socket8888 = new Socket("localhost", 8888);
        socket8888.close();
        Socket socket9999 = new Socket("localhost", 9999);
        socket9999.close();
    }
}
