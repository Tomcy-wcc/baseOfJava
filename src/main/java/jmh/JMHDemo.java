package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import thread.ForkJoinDemo;

import java.util.concurrent.*;

import static org.openjdk.jmh.annotations.Mode.AverageTime;


public class JMHDemo {

    public long add(long start, long end){
        long sum = 0;
        for(long i = start; i<= end; i++){
            sum+=i;
        }
        return sum;
    }

    @Benchmark
    @BenchmarkMode(AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void add(){
        /*ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo.CountTask countTask = new ForkJoinDemo.CountTask(0, 20000000000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(countTask);
        try {
            Long result = submit.get();
            //System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        add(0, 20000000000L);
        //System.out.println(add);
    }

    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder()
                .include(JMHDemo.class.getSimpleName())
                .forks(1).build();
        new Runner(build).run();
    }
}
