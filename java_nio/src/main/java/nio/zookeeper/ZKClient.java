package nio.zookeeper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

public class ZKClient {
    private static  Selector selector = null;

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(10888);
        serverSocketChannel.bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        boolean isRun = true;


        while (isRun){
            selector.select(1000);

            Set<SelectionKey> selectionKeys =  selector.selectedKeys();
            for(SelectionKey selectionKey:selectionKeys){
                //if((selectionKey.readyOps() & SelectionKey.OP_ACCEPT)!=0)
                if(selectionKey.isAcceptable()){

                }



            }
            selectionKeys.clear();

        }

    }
}
