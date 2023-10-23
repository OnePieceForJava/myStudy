package nio.zookeeper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ZKServer {

    private static Selector selector;
    private static  ServerSocketChannel serverSocketChannel;
    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object lock = new Object();


    public static void main(String[] args) throws IOException {

        serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(10888);
        serverSocketChannel.socket().bind(inetSocketAddress);
        //serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().setReuseAddress(true);//这个方法的作用

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);



        while (!serverSocketChannel.socket().isClosed()){
            selector.select(1000);


            Set<SelectionKey> selected;
            synchronized (lock) {
                selected = selector.selectedKeys();
            }

            for(SelectionKey selectionKey:selected){
                //if((selectionKey.readyOps() & SelectionKey.OP_ACCEPT)!=0)
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }



            }
            selected.clear();

        }

    }


}
