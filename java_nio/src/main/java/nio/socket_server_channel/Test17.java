package nio.socket_server_channel;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class Test17 {
    public static void main(String[] args) throws IOException {
        SelectorProvider provider1 = SelectorProvider.provider();
        System.out.println(provider1);

        /*ServerSocketChannel serverSocketChannel = null;
        serverSocketChannel = serverSocketChannel.open();*/

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        SelectorProvider provider2 = serverSocketChannel.provider();
        System.out.println(provider2);
        serverSocketChannel.close();

        //为什么SelectorProvider的俩个对象是一样的？ 单例？
    }
}
