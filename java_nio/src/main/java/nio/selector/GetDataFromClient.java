package nio.selector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class GetDataFromClient {

    @Test
    public void test1() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int keyCount = selector.select();
            Set<SelectionKey> set1 = selector.keys();
            Set<SelectionKey> set2 = selector.selectedKeys();

            Iterator<SelectionKey> iterator = set2.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel)selectionKey.channel();
                ServerSocket serverSocket = serverSocketChannel1.socket();
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                iterator.remove();//多个通道注册到同一个Selector中，可能会出现重复消费的情况
                reader.close();
                inputStreamReader.close();
                inputStream.close();
                socket.close();
            }
        }
    }

    @Test
    public void test2_1() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("2-1:我是中国人，我来自客户端！".getBytes()); socket.close();
    }
    @Test
    public void test2_2() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("2-2:我是中国人，我来自客户端！".getBytes()); socket.close();
    }
    @Test
    public void test2_3() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("2-3:我是中国人，我来自客户端！".getBytes()); socket.close();
    }
}
