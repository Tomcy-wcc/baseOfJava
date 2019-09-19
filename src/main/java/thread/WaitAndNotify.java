package thread;

public class WaitAndNotify {

    private static volatile boolean ready;
    private static int num;

    static class ReadThread extends Thread{

        @Override
        public void run() {
            while (!ready){
                System.out.println(Thread.currentThread());
                System.out.println(num);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadThread().start();
        Thread.sleep(1000);
        num = 42;
        Thread.sleep(1000);
        ready = true;
    }

}
/**
 * t1 wait
 * t2 wait
 * t1 end
 * t2 end
 * t4 wait
 * t3 wait
 */
