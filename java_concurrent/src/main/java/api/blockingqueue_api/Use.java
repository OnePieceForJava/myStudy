package api.blockingqueue_api;

import java.util.concurrent.PriorityBlockingQueue;

public class Use {


    public static void main(String[] args) {


        //Comparable<Integer>  Integer实现了Comparable接口，所以不需要
        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
        priorityBlockingQueue.add(1);
        priorityBlockingQueue.add(4);
        priorityBlockingQueue.add(3);
        priorityBlockingQueue.add(2);
        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.poll());


    }
}
