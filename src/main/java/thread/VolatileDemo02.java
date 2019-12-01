package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @auther wcc
 * @create 2019-09-16 9:38
 */
public class VolatileDemo02 {
    public static volatile int n = 0;
    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        for (int j = 1; j <= 1000; j++) {
                            n++;//不是原子操作, 所以要用锁保证原子操作
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(n);//n <= 10000，因为volatile不会保证原子性，会导致cpu操作丢失
    }
}
