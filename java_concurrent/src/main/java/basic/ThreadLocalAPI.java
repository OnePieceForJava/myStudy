package basic;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class ThreadLocalAPI {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            System.out.println(Thread.currentThread().getName()+" threadLocal initialValue");
            return 1L;
        }
    };


    public static void main(String[] args) {
        testWeakReference();
    }

    private static void testThreadLocalAPI(){
        //threadLocal.set(5l);
        Runnable r = ()->{
            System.out.println(threadLocal.get());
        };
        new Thread(r,"run1").start();
        System.out.println(threadLocal.get());
    }

    private static void testWeakReference(){
        new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("System gc");
            //-XX:+PrintGCDetails
            System.gc();
        }).start();

        //Fruit是个弱应用，Entry对象是个强引用，是不会被GC处理的
        Entry apple = new Entry(new Fruit("苹果"));
        for(int i=0;;i++){
            if( apple.get()!=null){
                System.out.println("Fruit 对象未被回收");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("Fruit 对象已被回收");
                return;
            }
        }
    }



    private static class Fruit{
        private String name;

        public Fruit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    private static class Entry extends WeakReference<Fruit>{
        /**
         * 继承WeakReference类后，一定得有一个有参构造函数
         * @param referent
         */
        public Entry(Fruit referent) {
            super(referent);
        }
    }
}
