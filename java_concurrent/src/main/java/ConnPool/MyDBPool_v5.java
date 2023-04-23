package ConnPool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyDBPool_v5 {


    /*
    private LinkedList<Connection> connectionLinkedList = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition getCondition = lock.newCondition();
    */

    private  BlockingQueue<Connection> poolQueue;


    public MyDBPool_v5(int corePoolSize) {
        poolQueue = new ArrayBlockingQueue<>(corePoolSize);
        for(int i=0;i<corePoolSize;i++){
            poolQueue.add(newConnection());
        }
    }


    public Connection getConnections() {
        long start = System.currentTimeMillis();
        Connection connection = null;
        try {
            connection = poolQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程"+Thread.currentThread().getName()+",成功获取连接,耗时："+(System.currentTimeMillis()-start));
        return connection;
    }


    public void releaseConnections(Connection conn) {
        long start = System.currentTimeMillis();
        try {
            poolQueue.put(conn);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程"+Thread.currentThread().getName()+",成功释放连接,耗时："+(System.currentTimeMillis()-start));

    }


    private Connection newConnection(){
        return new ConnectionImpl();
    }
}
