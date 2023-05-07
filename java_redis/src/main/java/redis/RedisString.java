package redis;

import redis.clients.jedis.Jedis;

public class RedisString {
    public static void main(String[] args) {
        Jedis jedis =new Jedis("127.0.0.1", 6379);
        //PONG
        System.out.println(jedis.ping());


        //set命令
        System.out.println(jedis.set("redis","Hello World!!!"));

        //get命令
        System.out.println(jedis.get("redis"));//key 存在，对应的value值
        System.out.println(jedis.get("not_exists")); //null  redis客户端返回（nil）


        //jedis.mset()
    }
}
