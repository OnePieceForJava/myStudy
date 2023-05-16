package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
    public void testSocketClint(){
        Jedis jedis = new Jedis("127.0.0.1",6380);
        jedis.set("hello","world");
    }


}
