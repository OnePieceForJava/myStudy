package basic;

/**
 * 类锁与对象锁
 */
public class SynClassAndSynInstance {

    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is running...");
            try {
                synClass();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class SynInstance implements Runnable {
        private SynClassAndSynInstance synClassAndSynInstance;

        public SynInstance(SynClassAndSynInstance synClassAndSynInstance) {
            this.synClassAndSynInstance = synClassAndSynInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running..." + synClassAndSynInstance);
            try {
                synClassAndSynInstance.instance();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized void instance() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("synInstance is going..." + this.toString());
        Thread.sleep(10000);
        System.out.println("synInstance ended " + this.toString());
    }

    private static synchronized void synClass() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("synClass going...");
        Thread.sleep(1000);
        System.out.println("synClass end");
    }

    public static void main(String[] args) throws InterruptedException {
        SynClassAndSynInstance synClassAndSynInstance = new SynClassAndSynInstance();
        Thread t1 = new SynClass();
        Thread t2 = new Thread(new SynInstance(synClassAndSynInstance));
        t2.start();
        Thread.sleep(1000);
        t1.start();
    }
}
