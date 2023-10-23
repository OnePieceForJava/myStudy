package nio.selection_key;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class $cancel {
    @Test
    public void server() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel1.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
        SocketChannel socketChannel = null;
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(8000);
                    //System.out.println(serverSocketChannel1.isOpen());
                    System.out.println("cancel() after selector.keys().size() = " + selector.keys().size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        boolean isRun = true;
        while (isRun == true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            System.out.println("cancel() before selector.keys().size() = " + selector.keys().size());
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    socketChannel = channel.accept();
                }
                key.cancel();
            }
        }
        serverSocketChannel1.close();
    }

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8888));
        socketChannel.close();
    }

}
