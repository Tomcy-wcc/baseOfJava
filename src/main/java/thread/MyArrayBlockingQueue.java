package thread;

import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现有界阻塞队列
 */
public class MyArrayBlockingQueue {

    //一个重入锁，用于锁住队列
    ReentrantLock lock = new ReentrantLock();

    //两个通知，通知可以进队出队
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    //队列
    private int[] queue;

    //队列容量
    private int capacity;

    //队列长度
    private int size;

    public MyArrayBlockingQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
    }

    /**
     * 进队
     * @param e
     * @return
     */
    public void offer(int e) {
        try {
            lock.lockInterruptibly();
            //如果队列满了，让线程等待
            while (size >= capacity){
                System.out.println("队列满了");
                notFull.await();
            }
            queue[size] = e;
            size++;
            System.out.println(e+"进入队列");
            notEmpty.signalAll();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 出队
     * @return
     */
    public void poll() {
        try {
            lock.lockInterruptibly();
            while (size == 0){
                System.out.println("队列为空");
                notEmpty.await();
            }
            int e = queue[0];
            int i;
            for(i = 1; i < size; i++){
                queue[i-1] = queue[i];
            }
            size--;
            notFull.signalAll();
            System.out.println(e+"出了队列");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyArrayBlockingQueue blockingQueue = new MyArrayBlockingQueue(10);
        new Thread(new PollThread(blockingQueue)).start();
        new Thread(new OfferThread(blockingQueue)).start();
        new Thread(new PollThread(blockingQueue)).start();
        new Thread(new OfferThread(blockingQueue)).start();
    }
}

/**
 * 出队
 */
class PollThread implements Runnable{

    private MyArrayBlockingQueue blockingQueue;

    public PollThread(MyArrayBlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            blockingQueue.poll();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

/**
 * 入队
 */
class OfferThread implements Runnable{
    private MyArrayBlockingQueue blockingQueue;

    public OfferThread(MyArrayBlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while(true){
            blockingQueue.offer(new Random().nextInt(100));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
