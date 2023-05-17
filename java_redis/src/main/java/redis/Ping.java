package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ping {
    public static void main(String[] args) {
        //Jedis jedis =new Jedis("127.0.0.1", 6379);
        Jedis jedis =new Jedis("192.168.42.111", 6379);
        String pingResult = jedis.ping();
        if("PONG".equals(pingResult)){
            System.out.println("连接成功");
        }
    }

    @Test
    public static void test() throws IOException {
        File file = new File("F:\test.txt");
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
    }
}
