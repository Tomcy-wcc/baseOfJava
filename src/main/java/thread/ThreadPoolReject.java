package thread;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolReject {

    public static class MyTask implements Runnable {

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "--->" + Thread.currentThread().getId());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(r.toString()+"开始执行任务");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(r.toString()+"执行任务完毕");
            }

        };

        for (int i = 1; i <= 100; i++) {
            executorService.execute(new MyTask("task" + i));
        }

        executorService.shutdown();
    }
}
