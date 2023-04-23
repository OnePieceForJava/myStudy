package basic;

public class VolatileNeeded {

    private static boolean flag;
    private static int num;


    static class PrintThread implements Runnable{

        @Override
        public void run() {
            System.out.println("PrintThread is run...");
            while (!flag){
                System.out.println("num = "+num);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new PrintThread()).start();
        flag = true;  //flag 变化了，PrintThread没有感知到
        num = 50;
        Thread.sleep(5000);
        System.out.println("main thread end...");
    }
}
