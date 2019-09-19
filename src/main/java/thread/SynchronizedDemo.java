package thread;

public class SynchronizedDemo implements Runnable{

    public static Integer i = 0;

    static SynchronizedDemo instance = new SynchronizedDemo();


    @Override
    public void run() {
        for(int j = 1; j <= 1000; j++){
            synchronized (SynchronizedDemo.class){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
