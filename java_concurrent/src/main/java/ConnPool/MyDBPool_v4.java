package ConnPool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyDBPool_v4 {

    private LinkedList<Connection> connectionLinkedList = new LinkedList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition getCondition = lock.newCondition();


    public MyDBPool_v4(int corePoolSize) {
        for(int i=0;i<corePoolSize;i++){
            connectionLinkedList.addLast(newConnection());
        }
    }


    public Connection getConnections() {
        Connection connection = null;
        lock.lock();
        try {
            Long start = null;
            boolean flag = false;//是否等待获取线程
            while(connectionLinkedList.isEmpty()){
                if(!flag){
                    flag = true;
                    System.out.println("线程"+Thread.currentThread().getName()+",连接池中已无连接，需要等待");
                    start = System.currentTimeMillis();
                }
                try {
                    getCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if(flag){
                System.out.println("线程"+Thread.currentThread().getName()+"，获取数据库连接共花费："+(System.currentTimeMillis()-start)+",线程池大小："+connectionLinkedList.size());
            }else{
                System.out.println("线程"+Thread.currentThread().getName()+"，线程未经等待直接获取到数据库连接,线程池大小："+connectionLinkedList.size());
            }
            connection = connectionLinkedList.removeFirst();
        } finally {
            lock.unlock();
        }
        return connection;
    }


    public void releaseConnections(Connection conn) {
       lock.lock();
       try{
           connectionLinkedList.addLast(conn);
           System.out.println("线程"+Thread.currentThread().getName()+"，释放数据库连接后，线程池大小:"+connectionLinkedList.size());
           getCondition.signalAll();
       }finally {
           lock.unlock();
       }
    }


    private Connection newConnection(){
        return new ConnectionImpl();
    }
}
