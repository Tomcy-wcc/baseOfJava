package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    public static void main(String[] args) {
        Ticket ticket = new Ticket(100);
        Lock lock = new ReentrantLock();
//        TicketWindow[] ticketWindows = new TicketWindow[4];
//        for (int i = 0; i < ticketWindows.length; i++) {
//            ticketWindows[i] = new TicketWindow(ticket, lock);
//            Thread thread = new Thread(ticketWindows[i]);
//            thread.setName("窗口" + (i + 1));
//            thread.start();
//        }
        TicketWindow ticketWindow = new TicketWindow(ticket, lock);
        new Thread(ticketWindow, "A").start();
        new Thread(ticketWindow, "B").start();
        new Thread(ticketWindow, "C").start();
        new Thread(ticketWindow, "D").start();
    }


}

/**
 * 买票窗口
 */
class TicketWindow implements Runnable {

    private Ticket ticket;
    private boolean isRun = true;
    private Lock lock;

    public TicketWindow(Ticket ticket, Lock lock) {
        this.ticket = ticket;
        this.lock = lock;
    }

    /**
     * 买票
     */
    public void sell() {

        //使用synchronized
        /*synchronized (ticket){
            int count = ticket.getCount();
            if(count <= 0){
                isRun = false;
                System.out.println("票卖完了");
            }else{
                ticket.setCount(count-1);
                System.out.println(Thread.currentThread().getName()+"卖了一张票，现在剩余票数: "+ticket.getCount());
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        //使用Lock
        if(lock.tryLock()){
            try {
                //lock.lock();
                int count = ticket.getCount();
                if (count <= 0) {
                    isRun = false;
                    System.out.println("票卖完了");
                } else {
                    ticket.setCount(count - 1);
                    System.out.println(Thread.currentThread().getName() + "卖了一张票，现在剩余票数: " + ticket.getCount());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("我是"+Thread.currentThread().getName()+"：别人占着锁，我不要了");
        }
    }


    @Override
    public void run() {
        while (isRun) {
            sell();
        }
    }

}

class Ticket {
    private int count;

    public Ticket(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
