package ConnPool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public class MyDBPool {
    private int corePoolsize;
    private List<Connection> connectionLinkedList = new LinkedList<>();

    public MyDBPool(int corePoolSize) {
        this.corePoolsize = corePoolSize;
    }


    public Connection getConnections() {
        synchronized (connectionLinkedList) {
            Connection connection;
            if (connectionLinkedList.size() < this.corePoolsize) {
                connection = new ConnectionImpl();
                connectionLinkedList.add(connection);
                System.out.println("线程名："+Thread.currentThread().getName()+",获取到连接池");
            } else {
                try {
                    System.out.println("线程名：" + Thread.currentThread().getName() + ",连接池已满,等待");
                    connectionLinkedList.wait();
                    connection = getConnections();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return connection;
        }
    }


    public void releaseConnections(Connection conn) {
        synchronized (connectionLinkedList) {
            System.out.println("线程名：" + Thread.currentThread().getName() + "释放连接,池大小：" + connectionLinkedList.size());
            connectionLinkedList.remove(conn);
            connectionLinkedList.notifyAll();
        }
    }
}
