package redis;

import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class RedisHash {
    public static void main(String[] args) {
        Jedis jedis =new Jedis("127.0.0.1", 6379);
        //PONG
        System.out.println(jedis.ping());
        //jedis.hset(key)


        //jedis.lpush()
        Class redis = jedis.getClass();



        Set<String> distinctMethod = new HashSet<String>();
        for(Method method :redis.getMethods()){
            distinctMethod.add(method.getName());
            //System.out.println(method.getName());
        }

        //385个命令
        System.out.println("-->"+distinctMethod.size());
        for(String methodName:distinctMethod){
            System.out.println(methodName);
        }
    }
}
