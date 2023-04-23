package basic;

public class ThreadInterrupt {

    public static void main(String[] args) {
        testSleepInterrupted();
    }

    /**
     * interrupt方法可以用来请求终止线程
     * 当对一个线程调用interrupt方法时，线程的中断状态将被置位。
     * 这是每一个线程都具有的boolean标志。每个线程都应该不时地检查这个标志，以判断线程是否被中断。
     *
     * 如果线程被阻塞，就无法检测中断状态。这是产生InterruptedException异常的地方。
     * 当在一个被阻塞的线程（调用sleep或wait）上调用interrupt方法时，
     * 阻塞调用将会被Interrupted Exception异常中断。
     *
     * 下面程序验证
     * sleep睡眠200秒，这是线程调用interrupt方法，产生InterruptedException异常，并将中断状态清空
     */
    private static void testSleepInterrupted(){
        Runnable r = ()->{
            System.out.println("sleep start...");
            try {
                Thread.sleep(200000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("catch InterruptedException-->"+Thread.currentThread().isInterrupted());
            }
        };

        Thread thread = new Thread(r);
        thread.start();
        thread.interrupt();
        System.out.println(Thread.currentThread().getName()+"-->end");
    }
}
