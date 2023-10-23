package nio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class BlockingMethod {

    @Test
    public void openServerSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        long acceptStart = System.currentTimeMillis();
        System.out.println("accept start ->"+acceptStart);
        //这是一个阻塞方法
        Socket socket = serverSocket.accept();
        long acceptEnd = System.currentTimeMillis();
        System.out.println("accept end ->"+acceptEnd);
        System.out.println("accept 耗时"+(acceptEnd-acceptStart)+"ms");

        InputStream inputStream = socket.getInputStream();
        byte[]  bytes = new byte[1024];
        long readStart = System.currentTimeMillis();
        System.out.println("read start ->"+readStart);
        inputStream.read(bytes);
        long readEnd = System.currentTimeMillis();
        System.out.println("read end ->"+readEnd);
        System.out.println("read 耗时"+(readEnd-readStart)+"ms");
        inputStream.close();
        socket.close();
        serverSocket.close();
    }

    @Test
    public void openClien() throws IOException, InterruptedException {

        //先启动openServerSocket方法，然后运行openClien
        //会发现accept方法处会阻塞
        Thread.sleep(10000);
        Socket socket = new Socket("127.0.0.1",12345);

        //测试read是个阻塞方法
        Thread.sleep(10000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("sleep 10000ms 后输入".getBytes());
        outputStream.close();
        socket.close();

    }

}
