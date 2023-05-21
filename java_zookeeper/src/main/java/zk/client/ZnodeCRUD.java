package zk.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Znode节点的增删改查操作
 */
public class ZnodeCRUD {
    private ZooKeeper zooKeeper = null;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private Watcher watcher = null;

    public ZnodeCRUD() {
        this.watcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.EventType eventType = event.getType();
                Event.KeeperState state = event.getState();
                if (Event.KeeperState.SyncConnected.equals(state)) {
                    countDownLatch.countDown();
                    System.out.println("zooKeeper Session state" + zooKeeper.getState());
                }
            }
        };
        System.out.println("Watcher 初始化完成...");
    }


    /**
     *
     */
    public void connect() {
        try {
            if(zooKeeper == null ){
                zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, this.watcher);
                countDownLatch.await();
            }else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void tryConnect() {
        try {
            if(zooKeeper == null ){
                zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, this.watcher);
                countDownLatch.await();
            }else {
                ZooKeeper.States states = zooKeeper.getState();
                if(!ZooKeeper.States.CONNECTED.equals(states)){

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void releaseConnect() {
        if (zooKeeper != null) {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void create(String path, String data, List<ACL> acl, CreateMode createMode) {
        try {

            String createReturnResult = zooKeeper.create(path, data.getBytes("utf-8"), acl, createMode);
            System.out.println("");
            System.out.println("create方法返回值--->" + createReturnResult);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void get(String path) {
        Stat stat = new Stat();
        try {
            byte[] data = zooKeeper.getData(path, null, stat);
            System.out.println("get-->" + new String(data, "utf-8"));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
            System.out.println("字符集不支持");
        }
    }

    public void set(String path, String newData) {
        try {
            Stat stat = zooKeeper.setData(path, newData.getBytes("utf-8"), -1);
            System.out.println("set stat信息-->" + stat.toString());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("字符集不支持");
        }
    }


    public void delete(String path) {
        try {
            zooKeeper.delete(path, -1);
            System.out.println("path" + path + ",已被删除");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ZnodeCRUD znodeCRUD = new ZnodeCRUD();
        znodeCRUD.connect();
        try {

            System.out.println("create() start------------------------------------------------------------------------");
            String path = "/zk_crud";
            znodeCRUD.create(path, "我是节点初始内容", ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("create() end------------------------------------------------------------------------");

            znodeCRUD.get(path);

            znodeCRUD.set(path, "我是变更后的节点信息");

            znodeCRUD.create(path+"/child", "我是/zk_crud节点的子节点", ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            znodeCRUD.delete(path);





        } finally {
            znodeCRUD.releaseConnect();
        }
    }
}
