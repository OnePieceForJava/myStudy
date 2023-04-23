package ConnPool;

import java.sql.Connection;
import java.util.Random;

public class DBPoolTest {
    public static void main(String[] args) {
        testMyDBPool();
    }
    private static MyDBPool_v5 myDBPool = new MyDBPool_v5(10);

    static class ConnectionUsage implements Runnable{
        @Override
        public void run() {
            Connection connection = myDBPool.getConnections();
            try {
                Random r = new Random();
                long l = r.nextInt(1000);
                Thread.sleep(l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myDBPool.releaseConnections(connection);
        }
    }
    private  static void testMyDBPool() {
        //开启100线程，持有[0,1000)随机毫秒连接
        for(int i =1;i<=100;i++){
            String threadName = "连接线程"+i;
            Thread thread = new Thread(new ConnectionUsage(),threadName);
            thread.start();
        }
    }
}
