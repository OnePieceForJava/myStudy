package nio.server_socket_api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class BackLog {

    @Test
    public void server() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8088, 3);
        // sleep(5000)的作用是不让ServerSocket调用accept()方法，
        // 而是由客户端Socket先发起10个连接请求
        // 然后在执行accept()方法时只能接收3个连接
        Thread.sleep(5000);
        System.out.println("accept1 begin");
        Socket socket1 = serverSocket.accept();
        System.out.println("accept1 end");
        System.out.println("accept2 begin");
        Socket socket2 = serverSocket.accept();
        System.out.println("accept2 end");
        System.out.println("accept3 begin");
        Socket socket3 = serverSocket.accept();
        System.out.println("accept3 end");
        System.out.println("accept4 begin");
        Socket socket4 = serverSocket.accept();
        System.out.println("accept4 end");
        System.out.println("accept5 begin");
        Socket socket5 = serverSocket.accept();
        System.out.println("accept5 end");
        socket1.close();
        socket2.close();
        socket3.close();
        socket4.close();
        socket5.close();
        serverSocket.close();
    }

    @Test
    public void client() throws IOException {
        Socket socket1 = new Socket("localhost", 8088);
        Socket socket2 = new Socket("localhost", 8088);
        Socket socket3 = new Socket("localhost", 8088);
        Socket socket4 = new Socket("localhost", 8088);
        Socket socket5 = new Socket("localhost", 8088);
        Socket socket6 = new Socket("localhost", 8088);
        Socket socket7 = new Socket("localhost", 8088);
        Socket socket8 = new Socket("localhost", 8088);
    }

    @Test
    public void addr() throws UnknownHostException, UnsupportedEncodingException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostName());
        //System.out.println(inetAddress.getHos());
        //System.out.println(new String(inetAddress.getAddress(),"utf-8"));
    }

    @Test
    public void test() throws IOException {
        ServerSocket serverSocket = new ServerSocket();

        //InetAddress inetAddress = new InetAddress();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8088);
        serverSocket.bind(inetSocketAddress);

        serverSocket.bind(inetSocketAddress, 50);

        serverSocket.getLocalPort();
        InetAddress inetAddress = serverSocket.getInetAddress();
        System.out.println(inetAddress.getHostAddress());
        InetSocketAddress inetSocketAddress1 = (InetSocketAddress)serverSocket.getLocalSocketAddress();
        //System.out.println(socketAddress.);
    }

    @Test
    public void test2() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        System.out.println("new ServerSocket()无参构造的端口是：" + serverSocket.getLocalPort());
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8888));
        System.out.println("调用完bind方法之后的端口是：" + serverSocket.getLocalPort());
        InetSocketAddress inetSocketAddress = (InetSocketAddress)serverSocket.getLocalSocketAddress();
        System.out.println("inetSocketAddress.getHostName=" + inetSocketAddress.getHostName());
        System.out.println("inetSocketAddress.getHostString=" + inetSocketAddress.getHostString());
        System.out.println("inetSocketAddress.getPort=" + inetSocketAddress.getPort());
        serverSocket.close();
    }
}
