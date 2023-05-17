package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

public class UseJedisPipeline {

    @Test
    public void pipelineUsageWithoutResult() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.42.111", 6379);
            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("连接失败");
            }
            Pipeline pipeline = jedis.pipelined();
            for (int i = 0; i < 100; i++){
                pipeline.set("n" + i, String.valueOf(i));
            }
            pipeline.sync();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Test
    public void pipelineUsageWithResult() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.42.111", 6379);
            if (!"PONG".equals(jedis.ping())) {
                throw new RuntimeException("连接失败");
            }
            Pipeline pipeline = jedis.pipelined();
            for (int i = 0; i < 100; i++){
                pipeline.set("n" + i, String.valueOf(i));
            }
            for (int i = 0; i < 100; i++){
                pipeline.get("n" + i);
            }
            List<Object>  results = pipeline.syncAndReturnAll();
            for(Object result:results){
                System.out.println(result);
            }
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
