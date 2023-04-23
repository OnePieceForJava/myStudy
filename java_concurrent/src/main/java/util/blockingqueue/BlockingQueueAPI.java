package util.blockingqueue;

import com.sun.org.apache.xalan.internal.xsltc.dom.SortingIterator;
import sun.security.provider.ConfigFile;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueAPI {


    public static void main(String[] args) {
        //test1();
        //testAdd();
        //testOffer();
        //testRemove();
        //testPoll();
        //testElementAndPeek();
        //testPut();
        testTake();
        //LinkedBlockingQueue
    }

    private static void test1() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.add(1));
        System.out.println(arrayBlockingQueue.offer(2));
        try {
            arrayBlockingQueue.put(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(arrayBlockingQueue.peek());
        System.out.println(arrayBlockingQueue.peek());
    }


    private static void testAdd(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);
        arrayBlockingQueue.add(3);
        System.out.println("队列已插满");
        arrayBlockingQueue.add(1);
        /**
         * result
         * 队列已插满
         * Exception in thread "main" java.lang.IllegalStateException: Queue full
         */
    }

    private static void testOffer(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer(1));
        System.out.println(arrayBlockingQueue.offer(2));
        System.out.println(arrayBlockingQueue.offer(3));
        System.out.println(arrayBlockingQueue.offer(4));
        /**
         * true
         * true
         * true
         * false
         */
    }


    private static void testRemove(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);
        arrayBlockingQueue.add(3);
        arrayBlockingQueue.remove();
        arrayBlockingQueue.remove();
        arrayBlockingQueue.remove();
        arrayBlockingQueue.remove();
        /**
         * Exception in thread "main" java.util.NoSuchElementException
         */
    }

    private static void testPoll(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer(1));
        System.out.println(arrayBlockingQueue.offer(2));
        System.out.println(arrayBlockingQueue.offer(3));
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        /**
         * true
         * true
         * 1
         * 2
         * 3
         * null
         */
    }


    private static void testElementAndPeek(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("offer 1,"+arrayBlockingQueue.offer(1));
        System.out.println("offer 2,"+arrayBlockingQueue.offer(1));
        System.out.println("offer 3,"+arrayBlockingQueue.offer(1));
        System.out.println("peek(),"+arrayBlockingQueue.peek());
        System.out.println("element(),"+arrayBlockingQueue.element());
        System.out.println("poll() 1,"+arrayBlockingQueue.poll());
        System.out.println("poll() 2,"+arrayBlockingQueue.poll());
        System.out.println("poll() 3,"+arrayBlockingQueue.poll());
        System.out.println("peek(),"+arrayBlockingQueue.peek());
        System.out.println("element(),"+arrayBlockingQueue.element());
        /**
         * offer 1,true
         * offer 2,true
         * offer 3,true
         * peek(),1
         * element(),1
         * poll() 1,1
         * poll() 2,1
         * poll() 3,1
         * peek(),null
         * Exception in thread "main" java.util.NoSuchElementException
         */
    }


    private static void testPut(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("offer 1,"+arrayBlockingQueue.offer(1));
        System.out.println("offer 2,"+arrayBlockingQueue.offer(1));
        System.out.println("offer 3,"+arrayBlockingQueue.offer(1));

        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName()+"线程启动，休眠10秒");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"休眠10秒结束，并从队列中移除一个元素"+arrayBlockingQueue.remove());
        };
        new Thread(r).start();

        System.out.println("put 4 start....");
        try {
            arrayBlockingQueue.put(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("put 4 end....");

        /**
         * offer 1,true
         * offer 2,true
         * offer 3,true
         * put 4 start....
         * Thread-0线程启动，休眠10秒
         * Thread-0休眠10秒结束，并从队列中移除一个元素
         * put 4 end....
         */
    }

    private static void testTake(){
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        /*System.out.println("offer 1,"+arrayBlockingQueue.offer(1));
        System.out.println("offer 2,"+arrayBlockingQueue.offer(1));
        System.out.println("offer 3,"+arrayBlockingQueue.offer(1));*/

        Runnable r = ()->{
            System.out.println(Thread.currentThread().getName()+"线程启动，休眠10秒");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"休眠10秒结束，并往队列中插入一个元素"+arrayBlockingQueue.offer(1));
        };
        new Thread(r).start();

        System.out.println("take() start....");
        try {
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("take() end....");
        System.out.println("peek(),"+arrayBlockingQueue.peek());

        /**
         * take() start....
         * Thread-0线程启动，休眠10秒
         * Thread-0休眠10秒结束，并往队列中插入一个元素true
         * take() end....
         * peek(),null
         */
    }
}
