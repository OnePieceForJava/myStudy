package nio.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class MutiDataTransfer {

    @Test
    public void openServerSocket() throws IOException, InterruptedException {
        char[] charBuffer = new char[15];
        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println("server阻塞开始=" + System.currentTimeMillis());
        Socket socket = serverSocket.accept();
        System.out.println("server阻塞结束=" + System.currentTimeMillis());

        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        System.out.println("serverB begin " + System.currentTimeMillis());
        int readLength = inputStreamReader.read(charBuffer);
        System.out.println("serverB end " + System.currentTimeMillis());
        while (readLength != -1) {
            System.out.println(new String(charBuffer, 0, readLength) + " while " + System.currentTimeMillis());
            readLength = inputStreamReader.read(charBuffer);
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
        System.out.println("server端运行结束=" + System.currentTimeMillis());
    }

    @Test
    public void openClien() throws IOException, InterruptedException {
        System.out.println("client连接准备=" + System.currentTimeMillis());
        Socket socket = new Socket("localhost", 8088);
        System.out.println("client连接结束=" + System.currentTimeMillis());
        Thread.sleep(2000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我是高洪岩1".getBytes());
        Thread.sleep(3000);
        outputStream.write("我是高洪岩2".getBytes());
        Thread.sleep(3000);
        outputStream.write("我是高洪岩3".getBytes());
        Thread.sleep(3000);
        outputStream.write("我是高洪岩4".getBytes());
        Thread.sleep(3000);
        outputStream.write("我是高洪岩5".getBytes());
        System.out.println("client close begin=" + System.currentTimeMillis());
        outputStream.close();
        socket.close();
        System.out.println("client close end=" + System.currentTimeMillis());
    }

}
