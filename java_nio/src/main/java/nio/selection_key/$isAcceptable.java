package nio.selection_key;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class $isAcceptable {

    @Test
    public void test1() throws IOException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel1.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey1 = serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);

        boolean isRun = true;
        while (isRun == true) {
            selector.select();

            Set<SelectionKey> selectedKeysSet = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectedKeysSet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                Socket socket = null;
                if (key.isAcceptable()) {
                    socket = channel.socket().accept();
                    System.out.println("server isAcceptable()");
                }
                socket.getInputStream();
                socket.close();
                iterator.remove();
            }
        }
        serverSocketChannel1.close();
    }

}
