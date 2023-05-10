package api.blockingqueue_api;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class UseArrayBlockingQueue {


    public static void main(String[] args) {
        /*插入*/
        //useAdd();
        //useOffer();
        //usePut();
        //useOfferTimeUnit();

        /*移除*/
        //useRemove();
        //usePoll();
        //useTake();
        //usePollTimeUnit();
        useElementAndPeek();
    }

    /**
     * 队列已插满
     * Exception in thread "main" java.lang.IllegalStateException: Queue full
     */
    private static void useAdd() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add(1));
        System.out.println(arrayBlockingQueue.add(2));
        System.out.println(arrayBlockingQueue.add(3));
        System.out.println("队列已插满");
        System.out.println(arrayBlockingQueue.add(4));

    }

    /**
     * true
     * true
     * true
     * false
     */
    private static void useOffer() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer(1));
        System.out.println(arrayBlockingQueue.offer(2));
        System.out.println(arrayBlockingQueue.offer(3));
        System.out.println(arrayBlockingQueue.offer(4));

    }


    /**
     * offer 1-->true
     * offer 2-->true
     * offer 3-->true
     * put 4 start....     #put()方法执行了，但队列满了，所以阻塞了，必须等移除一个元素后才能解决阻塞
     * Thread-0线程启动，休眠10秒
     * Thread-0休眠10秒结束，并从队列中移除一个元素1
     * put 4 end....
     */
    private static void usePut() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("offer 1-->" + arrayBlockingQueue.offer(1));
        System.out.println("offer 2-->" + arrayBlockingQueue.offer(2));
        System.out.println("offer 3-->" + arrayBlockingQueue.offer(3));

        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "线程启动，休眠10秒");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "休眠10秒结束，并从队列中移除一个元素" + arrayBlockingQueue.remove());
        };
        new Thread(r).start();

        //put(4)
        System.out.println("put 4 start....");
        try {
            arrayBlockingQueue.put(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("put 4 end....");
    }


    /**
     * offer 1-->true
     * offer 2-->true
     * offer 3-->true
     * offer 4 after 5s start                      ##offer 4， 5s后超时
     * Thread-0线程启动，休眠10秒
     * offer 4-->false                             ##队列仍然是满了，无法插入
     * offer 4 after 5s end                        ##offer 5，10s后超时
     * offer 5 after 10s start
     * Thread-0休眠10秒结束，并从队列中移除一个元素1     ##移除一个元素
     * offer 5-->true                              ##队列已不满，可以成功插入
     * offer 5 after 10s end
     */
    private static void useOfferTimeUnit() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("offer 1-->" + arrayBlockingQueue.offer(1));
        System.out.println("offer 2-->" + arrayBlockingQueue.offer(2));
        System.out.println("offer 3-->" + arrayBlockingQueue.offer(3));

        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "线程启动，休眠10秒");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "休眠10秒结束，并从队列中移除一个元素" + arrayBlockingQueue.remove());
        };
        new Thread(r).start();

        System.out.println("offer 4 after 5s start");
        try {
            System.out.println("offer 4-->" + arrayBlockingQueue.offer(4, 5, TimeUnit.SECONDS));

        } catch (InterruptedException e) {
            System.out.println("抛出InterruptedException...");
            throw new RuntimeException("抛出InterruptedException...");
        }
        System.out.println("offer 4 after 5s end");


        System.out.println("offer 5 after 10s start");
        try {
            System.out.println("offer 5-->" + arrayBlockingQueue.offer(5, 10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            System.out.println("抛出InterruptedException...");
            throw new RuntimeException("抛出InterruptedException...");
        }
        System.out.println("offer 5 after 10s end");
    }


    /**
     * add 1-->true
     * add 2-->true
     * add 3-->true
     * remove-->1
     * remove-->2
     * remove-->3
     * Exception in thread "main" java.util.NoSuchElementException
     * at java.util.AbstractQueue.remove(AbstractQueue.java:117)
     * at api.blockingqueue_api.UseArrayBlockingQueue.useRemove(UseArrayBlockingQueue.java:166)
     * at api.blockingqueue_api.UseArrayBlockingQueue.main(UseArrayBlockingQueue.java:19)
     */
    private static void useRemove() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("add 1-->" + arrayBlockingQueue.add(1));
        System.out.println("add 2-->" + arrayBlockingQueue.add(2));
        System.out.println("add 3-->" + arrayBlockingQueue.add(3));
        System.out.println("remove-->" + arrayBlockingQueue.remove());
        System.out.println("remove-->" + arrayBlockingQueue.remove());
        System.out.println("remove-->" + arrayBlockingQueue.remove());
        System.out.println("remove-->" + arrayBlockingQueue.remove());
    }

    /**
     * add 1-->true
     * add 2-->true
     * add 3-->true
     * poll-->1
     * poll-->2
     * poll-->3
     * poll-->null
     */
    private static void usePoll() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("add 1-->" + arrayBlockingQueue.add(1));
        System.out.println("add 2-->" + arrayBlockingQueue.add(2));
        System.out.println("add 3-->" + arrayBlockingQueue.add(3));
        System.out.println("poll-->" + arrayBlockingQueue.poll());
        System.out.println("poll-->" + arrayBlockingQueue.poll());
        System.out.println("poll-->" + arrayBlockingQueue.poll());
        System.out.println("poll-->" + arrayBlockingQueue.poll());

    }


    /**
     * take() start....                               ##但队列为空，无法取出元素，main线程阻塞
     * Thread-0线程启动，休眠10秒
     * Thread-0休眠10秒结束，并往队列中插入一个元素true
     * take() end....                                ##直到队列中放入元素后，main线程才解除阻塞状态
     */
    private static void useTake() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "线程启动，休眠10秒");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "休眠10秒结束，并往队列中插入一个元素" + arrayBlockingQueue.offer(1));
        };
        new Thread(r).start();

        System.out.println("take() start....");
        try {
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("take() end....");
    }


    /**
     * poll() after 5s start....
     * Thread-0线程启动，休眠10秒
     * poll() after 5s-->null
     * take() after 5s end....
     * poll() after 10s start....
     * Thread-0休眠10秒结束，并往队列中插入一个元素true
     * poll() after 10s-->1
     * take() after 10s end....
     */
    private static void usePollTimeUnit() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "线程启动，休眠10秒");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "休眠10秒结束，并往队列中插入一个元素" + arrayBlockingQueue.offer(1));
        };
        new Thread(r).start();

        System.out.println("poll() after 5s start....");
        try {
            System.out.println("poll() after 5s-->" + arrayBlockingQueue.poll(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("take() after 5s end....");


        System.out.println("poll() after 10s start....");
        try {
            System.out.println("poll() after 10s-->" + arrayBlockingQueue.poll(10, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("take() after 10s end....");
    }


    /**
     * ffer 1-->true
     * offer 2-->true
     * offer 3-->true
     * peek()-->1
     * element()-->1
     * poll()-->1
     * poll()-->2
     * poll()-->3
     * peek(),null
     * Exception in thread "main" java.util.NoSuchElementException
     * 	at java.util.AbstractQueue.element(AbstractQueue.java:136)
     * 	at api.blockingqueue_api.UseArrayBlockingQueue.useElementAndPeek(UseArrayBlockingQueue.java:286)
     * 	at api.blockingqueue_api.UseArrayBlockingQueue.main(UseArrayBlockingQueue.java:23)
     *
     * Process finished with exit code 1
     */
    private static void useElementAndPeek() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("offer 1-->" + arrayBlockingQueue.offer(1));
        System.out.println("offer 2-->" + arrayBlockingQueue.offer(2));
        System.out.println("offer 3-->" + arrayBlockingQueue.offer(3));
        System.out.println("peek()-->" + arrayBlockingQueue.peek());
        System.out.println("element()-->" + arrayBlockingQueue.element());
        System.out.println("poll()-->" + arrayBlockingQueue.poll());
        System.out.println("poll()-->" + arrayBlockingQueue.poll());
        System.out.println("poll()-->" + arrayBlockingQueue.poll());
        System.out.println("peek()," + arrayBlockingQueue.peek());
        System.out.println("element()," + arrayBlockingQueue.element());
    }
}
