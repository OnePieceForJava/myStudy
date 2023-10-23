package nio.socket_server_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Test16 {
    public static void main(String[] args) throws IOException {
        /*ServerSocketChannel*/
        //open()
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //bind()
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        //configureBlocking
        serverSocketChannel.configureBlocking(false);


        /*Selector*/
        //open
        Selector selector = Selector.open();

        //ServerSocketChannel.register
        SelectionKey selectionKey1 = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("A=" + selectionKey1 + " " + selectionKey1.hashCode());
        //ServerSocketChannel keyFor -->SelectionKey对象
        SelectionKey selectionKey2 = serverSocketChannel.keyFor(selector);
        System.out.println("B=" + selectionKey2 + " " + selectionKey2.hashCode());

        System.out.println(System.identityHashCode(selectionKey1) == System.identityHashCode(selectionKey2));
        serverSocketChannel.close();
    }
}
