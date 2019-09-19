package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 采用到到计数器模拟火箭发射过程
 * 1、模拟检查
 * 2、发射
 */
public class CountDownLatchDemo implements Runnable{

    //假如有10项检查工作
    static CountDownLatch downLatch = new CountDownLatch(10);
    static CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始检查");

        //设置检查需要的时间
        try {
            Thread.sleep(new Random().nextInt(10) *1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"检查结束");
        //检查结束，到计数器进行减一操作
        downLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 1; i <= 10; i++){
            executorService.submit(countDownLatchDemo);
        }
        //检查,直到10个线程检查结束后，解除阻塞状态
        downLatch.await();
        System.out.println("Fire");
        executorService.shutdown();
    }
}
