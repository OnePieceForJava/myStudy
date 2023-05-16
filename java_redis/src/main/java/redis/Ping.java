package redis;

import redis.clients.jedis.Jedis;

public class Ping {
    public static void main(String[] args) {
        //Jedis jedis =new Jedis("127.0.0.1", 6379);
        Jedis jedis =new Jedis("192.168.42.111", 6379);
        //PONG
        System.out.println(jedis.ping());


        jedis.set("ni","hao");
        jedis.shutdown();
    }
}
