package zk.client;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class TestCreateSession {

    private static String ZOOKEEPER_IP = "127.0.0.1";

    private static int SESSION_TIME_OUT = 30000;

    @Test
    public void testCreateSession() throws InterruptedException {

        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_IP, SESSION_TIME_OUT, null);
            System.out.println(zooKeeper);
            System.out.println("zookeeper state:" + zooKeeper.getState());

            //State:CONNECTING sessionid:0x0 local:null remoteserver:null lastZxid:0 xid:1 sent:0 recv:0 queuedpkts:0 pendingresp:0 queuedevents:0
            //zookeeper state:CONNECTING
            Thread.sleep(10000);
            System.out.println("sleep 10s ,zookeeper state:" + zooKeeper.getState());
            //sleep 10s ,zookeeper state:CONNECTED
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testCreateZnode() throws InterruptedException {

        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_IP, SESSION_TIME_OUT, null);
            System.out.println(zooKeeper);
            System.out.println("zookeeper state:" + zooKeeper.getState());
            //State:CONNECTING sessionid:0x0 local:null remoteserver:null lastZxid:0 xid:1 sent:0 recv:0 queuedpkts:0 pendingresp:0 queuedevents:0
            //zookeeper state:CONNECTING
            System.out.println(zooKeeper.create("/java_zookeeper", "dataValue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }


    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    @Test
    public void testCreateConnectedSession() throws InterruptedException {

        try {
            ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_IP, SESSION_TIME_OUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        countDownLatch.countDown();
                        System.out.println("zookeeper seseion state change to CONNECTED");
                    }
                }
            });
            //等待Session的状态由 CONNECTING 变成 CONNECTED
            countDownLatch.await();
            System.out.println(zooKeeper.getState());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
