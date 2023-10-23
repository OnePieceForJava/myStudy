package redis;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class MyRedisServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6380);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] b = new byte[2048];
            inputStream.read(b);
            System.out.println(new String(b,"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSocketClint() throws Exception {
        Jedis jedis = new Jedis("127.0.0.1",6380);
        jedis.set("hello","world");
        /*File file = new File("F://test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write("*3\r\n".getBytes("utf-8"));
        fileOutputStream.write("$3\r\n".getBytes("utf-8"));
        fileOutputStream.write("set\r\n".getBytes("utf-8"));
        fileOutputStream.write("$5\r\n".getBytes("utf-8"));
        fileOutputStream.write("hello\r\n".getBytes("utf-8"));
        fileOutputStream.write("$5\r\n".getBytes("utf-8"));
        fileOutputStream.write("world\r\n".getBytes("utf-8"));
        fileOutputStream.flush();
        fileOutputStream.close();

        File file2 = new File("F://test2.txt");
        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
        fileOutputStream2.write("*3\r\n$3\r\nset\r\n$5\r\nhello\r\n$5\r\nworld\r\n".getBytes("utf-8"));
        fileOutputStream.flush();
        fileOutputStream.close();*/
    }


}
