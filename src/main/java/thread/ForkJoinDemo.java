package thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo {


    public static class CountTask extends RecursiveTask<Long> {

        //设置处理阈值
        private long threshold = 100000000L;

        //任务的开始位置
        private long start;

        //任务的结束结束位置
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            //判断是否超过阈值
            boolean canCompute = (end - start + 1) <= threshold;
            if (canCompute) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                //分为100个小任务进行计算
                long step = (end - start) / 100;
                long pos = start;
                ArrayList<RecursiveTask<Long>> recursiveTasks = new ArrayList<>();
                for(int i = 1; i <= 100; i++){
                    long lastPos = pos + step;
                    if(lastPos > end) lastPos = end;
                    CountTask countTask = new CountTask(pos, lastPos);
                    pos = lastPos + 1;
                    recursiveTasks.add(countTask);
                    countTask.fork();
                }

                for(RecursiveTask<Long> recursiveTask : recursiveTasks){
                    sum += recursiveTask.join();
                }

            }
            return sum;
        }
    }

    public static long add(long start, long end){
        long sum = 0;
        for(long i = start; i<= end; i++){
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args) {
        //System.out.println(add(0, 2000000000L));


        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(0, 2000000000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(countTask);
        try {
            Long result = submit.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        forkJoinPool.shutdown();


    }

}
