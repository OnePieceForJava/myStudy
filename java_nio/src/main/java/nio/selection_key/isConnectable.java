package nio.selection_key;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class isConnectable {
    @Test
    public void test1() throws IOException, InterruptedException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey1 = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        boolean isRun = true;


        socketChannel.connect(new InetSocketAddress("localhost", 8888));
        while (isRun == true) {
            System.out.println("*********************");
            int keyCount = selector.select();

            Set<SelectionKey> selectedKeysSet = selector.selectedKeys();
            System.out.println("selectedKeysSet.size()-->"+selectedKeysSet.size());
            Iterator<SelectionKey> iterator = selectedKeysSet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                System.out.println("isReadable()-->"+key.isReadable());
                System.out.println("isWritable()-->"+key.isWritable());
                System.out.println("isConnectable()-->"+key.isConnectable());
                if (key.isConnectable()) {
                    System.out.println("client isConnectable()");
                    // 需要在此处使用finishConnect()方法完成连接，
                    // 因为socketChannel是非阻塞模式
                    while (!socketChannel.finishConnect()) {
                        System.out.println("! socketChannel.finishConnect()");
                    }
                    key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    //SocketChannel channel = (SocketChannel)key.channel();
                    /*int count = channel.read(ByteBuffer.allocate(4));
                    System.out.println(count);
                    channel.close();*/
                }
                iterator.remove();
            }
            Thread.sleep(5000);
            System.out.println("*********************");
        }
        socketChannel.close();
        System.out.println("");
    }
}
