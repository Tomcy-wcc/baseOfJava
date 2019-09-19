package thread;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool
 * 1、scheduleWithFixedDelay()
 *      一个周期的时间为任务花费的时间+延迟的时间
 * scheduleAtFixedRate()
 *      如果period > 任务花费的时间，则一个周期为period 否者为任务花费的时间
 *
 */
public class ThreadPoolDemo implements Runnable {

    public static void main(String[] args) {
       /* ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 1; i <= 10; i++){
            executorService.submit(new ThreadPoolDemo());
        }
        executorService.shutdown();*/

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleWithFixedDelay(new ThreadPoolDemo(), 0, 4, TimeUnit.SECONDS);
        //scheduledExecutorService.scheduleAtFixedRate(new ThreadPoolDemo(), 0, 4, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "--->" + LocalTime.now().getSecond());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
