package ConnPool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyDBPool_v2 {
    private int corePoolsize;
    private List<Connection> connectionLinkedList = new LinkedList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition poolSizeCondition = lock.newCondition();

    public MyDBPool_v2(int corePoolSize) {
        this.corePoolsize = corePoolSize;

    }


    public Connection getConnections() {
        Connection connection;
        lock.lock();
        try {
            if (connectionLinkedList.size() < this.corePoolsize) {
                connection = new ConnectionImpl();
                connectionLinkedList.add(connection);
                System.out.println("线程名："+Thread.currentThread().getName()+",获取到连接池");
            } else {
                try {
                    System.out.println("线程名：" + Thread.currentThread().getName() + ",连接池已满,等待");
                    poolSizeCondition.await();
                    connection = getConnections();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }finally {
            lock.unlock();
        }
        return connection;
    }


    public void releaseConnections(Connection conn) {
        lock.lock();
        try {
            System.out.println("线程名：" + Thread.currentThread().getName() + "释放连接,池大小：" + connectionLinkedList.size());
            connectionLinkedList.remove(conn);
            poolSizeCondition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
