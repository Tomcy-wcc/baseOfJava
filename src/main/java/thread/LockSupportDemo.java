package thread;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 基于信号量 一个资源只能一个线程访问 其他线程被阻塞
 */
public class LockSupportDemo {

    static Object object = new Object();

    static ReadThread r1 = new ReadThread("r1");
    static ReadThread r2 = new ReadThread("r2");

    public static class ReadThread extends Thread{

        public ReadThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object){
                System.out.println("in"+getName());
                //信号量-1
                LockSupport.park();
                if(Thread.interrupted()){
                    System.out.println(getName()+"被中断");
                }
            }
            System.out.println(getName()+"等待结束");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        r1.start();
        Thread.sleep(100);
        r2.start();
        r1.interrupt();
        LockSupport.unpark(r2);
    }

}
