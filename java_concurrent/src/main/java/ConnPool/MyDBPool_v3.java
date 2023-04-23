package ConnPool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyDBPool_v3 {
    private int corePoolsize;
    private List<Connection> connectionLinkedList = new LinkedList<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    private Condition condition = writeLock.newCondition();


    public MyDBPool_v3(int corePoolSize) {
        this.corePoolsize = corePoolSize;
    }


    public Connection getConnections() {
        Connection connection;
        writeLock.lock();
        try {
            if (connectionLinkedList.size() < this.corePoolsize) {
                connection = new ConnectionImpl();
                connectionLinkedList.add(connection);
                System.out.println("线程名："+Thread.currentThread().getName()+",获取到连接池");
            } else {
                try {
                    System.out.println("线程名：" + Thread.currentThread().getName() + ",连接池已满,等待");
                    condition.await();
                    connection = getConnections();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }finally {
            writeLock.unlock();
        }
        return connection;
    }


    public void releaseConnections(Connection conn) {
        lock.readLock();
        try {
            System.out.println("线程名：" + Thread.currentThread().getName() + "释放连接,池大小：" + connectionLinkedList.size());
            connectionLinkedList.remove(conn);
            condition.signalAll();
        }finally {
            lock.readLock();
        }
    }
}
