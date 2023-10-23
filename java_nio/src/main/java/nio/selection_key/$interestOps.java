package nio.selection_key;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

import org.junit.Test;

public class $interestOps {

    @Test
    public void test1() throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel1.configureBlocking(false);


        SocketChannel socketChannel1 = SocketChannel.open();
        socketChannel1.configureBlocking(false);

        SocketChannel socketChannel2 = SocketChannel.open();
        socketChannel2.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey key1 = serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey key2 = socketChannel1.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
        SelectionKey key3 = socketChannel2.register(selector,SelectionKey.OP_CONNECT);

        System.out.println("OP_READ-->"+Integer.toBinaryString(SelectionKey.OP_READ));
        System.out.println("OP_WRITE-->"+Integer.toBinaryString(SelectionKey.OP_WRITE));
        System.out.println("OP_CONNECT-->"+Integer.toBinaryString(SelectionKey.OP_CONNECT));
        System.out.println("OP_ACCEPT-->"+Integer.toBinaryString(SelectionKey.OP_ACCEPT));


        System.out.println("~OP_READ-->"+Integer.toBinaryString(~SelectionKey.OP_READ));
        System.out.println("~OP_WRITE-->"+Integer.toBinaryString(~SelectionKey.OP_WRITE));
        System.out.println("~OP_CONNECT-->"+Integer.toBinaryString(~SelectionKey.OP_CONNECT));
        System.out.println("~OP_ACCEPT-->"+Integer.toBinaryString(~SelectionKey.OP_ACCEPT));



        System.out.println(~key1.interestOps() & SelectionKey.OP_ACCEPT);
        System.out.println(~key1.interestOps() & SelectionKey.OP_CONNECT);
        System.out.println(~key1.interestOps() & SelectionKey.OP_READ);
        System.out.println(~key1.interestOps() & SelectionKey.OP_WRITE);
        System.out.println();

        System.out.println(~key2.interestOps() & SelectionKey.OP_ACCEPT);
        System.out.println(~key2.interestOps() & SelectionKey.OP_CONNECT);
        System.out.println(~key2.interestOps() & SelectionKey.OP_READ);
        System.out.println(~key2.interestOps() & SelectionKey.OP_WRITE);
        System.out.println();
        System.out.println(~key3.interestOps() & SelectionKey.OP_ACCEPT);
        System.out.println(~key3.interestOps() & SelectionKey.OP_CONNECT);
        System.out.println(~key3.interestOps() & SelectionKey.OP_READ);
        System.out.println(~key3.interestOps() & SelectionKey.OP_WRITE);
        System.out.println();
        // 使用public abstract SelectionKey interestOps(intops)方法，
        // 重新定义感兴趣的事件
        System.out.println("isReadable()-->"+key3.isReadable());
        System.out.println("isWritable()-->"+key3.isWritable());
        System.out.println("isConnectable()-->"+key3.isConnectable());
        socketChannel2.connect(new InetSocketAddress("localhost",8888));
        if(key3.isConnectable()){
            if(socketChannel2.finishConnect()){
                System.out.println("finishConnect()");
            }
        }

        while (true){
            System.out.println("***************************");
            selector.select();
            System.out.println("isReadable()-->"+key3.isReadable());
            System.out.println("isWritable()-->"+key3.isWritable());
            System.out.println("isConnectable()-->"+key3.isConnectable());
            Set<SelectionKey> set = selector.selectedKeys();
            for(SelectionKey k:set){
               if(k.isConnectable()){
                   System.out.println("变更interest集");
                   k.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
               }
            }
            set.clear();
            Thread.sleep(10000);

        }




        /*key3.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT);
       *//* System.out.println(~key3.interestOps() & SelectionKey.OP_ACCEPT);
        System.out.println(~key3.interestOps() & SelectionKey.OP_CONNECT);
        System.out.println(~key3.interestOps() & SelectionKey.OP_READ);
        System.out.println(~key3.interestOps() & SelectionKey.OP_WRITE);*//*
        //selector.select();
        System.out.println(key3.isReadable());
        System.out.println(key3.isWritable());
        System.out.println(key3.isConnectable());*/
    }

}
