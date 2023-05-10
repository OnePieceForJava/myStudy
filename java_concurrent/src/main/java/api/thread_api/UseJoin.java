package api.thread_api;

public class UseJoin {
    public static void main(String[] args) throws InterruptedException {
        testThreadJoin();
    }

    private static  void testThreadJoin() throws InterruptedException {
        long start = System.currentTimeMillis();
        Runnable r = ()->{
            try {
                System.out.println(Thread.currentThread().getName()+" sleep start.");
                Thread.sleep(10000);
                System.out.println(Thread.currentThread().getName()+" sleep end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(r);
        thread.start();
        /*注意下面两者的区别*/
        //thread.join();
        thread.join(2000);
        System.out.println(Thread.currentThread().getName()+" end.");
        System.out.println(System.currentTimeMillis()-start);
    }
}
