package thread;

import java.util.concurrent.*;

public class ThreadPoolReject {

    public static class MyTask implements Runnable{

        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()+"--->"+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
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
                new LinkedBlockingDeque<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString() + " is discard");
                    }
                }){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(r.toString()+"开始执行任务");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(r.toString()+"执行任务完毕");
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
            
        };

        for(int i = 1; i <= 100; i++){
            executorService.execute(new MyTask("task"+i));
            Thread.sleep(10);
        }
        executorService.shutdown();

        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }
}
