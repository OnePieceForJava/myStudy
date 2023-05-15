package redis;

import redis.clients.jedis.Jedis;

import java.io.IOException;

public class RedisSet {
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = Runtime.getRuntime().exec("D:\\03-developKit\\Redis-x64-5.0.14.1\\redis-server.exe");
        Jedis jedis =new Jedis("127.0.0.1", 6379);
        //PONG
        System.out.println(jedis.ping());
        jedis.shutdown();
    }
}
