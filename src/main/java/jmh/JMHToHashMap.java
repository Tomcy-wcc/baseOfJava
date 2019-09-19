package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.openjdk.jmh.annotations.Mode.Throughput;

/**
 * 基于JMH比较 HashMap ConcurrentHashMap Collections.synchronizedMap(new HashMap<>())的get和size方法的性能
 *
 * 吞吐量：
 * Benchmark                                Mode  Cnt     Score     Error   Units
 * JMHToHashMap.testConcurrentHashMapGet   thrpt    5   175.631 ±  35.816  ops/us
 * JMHToHashMap.testConcurrentHashMapSize  thrpt    5   743.786 ± 113.906  ops/us
 * JMHToHashMap.testHashMapGet             thrpt    5   190.570 ±   3.010  ops/us
 * JMHToHashMap.testHashMapSize            thrpt    5  1505.667 ± 126.776  ops/us
 * JMHToHashMap.testSynchronizedMapGet     thrpt    5    45.305 ±   0.093  ops/us
 * JMHToHashMap.testSynchronizedMapSize    thrpt    5    43.107 ±  12.017  ops/us
 *
 * 结论：
 * get()方法：HashMap与ConcurrentHashMap吞吐量差不多，是synchronizedMap4倍
 * 原因：ConcurrentHashMap采用了避免线程竞争的CAS算法，所以跟HashMap差不多，而synchronizedMap采用了重入锁
 * size()方法：HashMap是ConcurrentHashMap两倍，是SynchronizedMap的35倍
 *
 */
@State(Scope.Thread)
@BenchmarkMode(Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class JMHToHashMap {

    static Map<String, Integer> hashMap = new HashMap<>();

    static Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    static Map<String, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

    //初始化三个Map
    @Setup
    public void setup(){
        for(int i = 1; i <= 1000; i++){
            hashMap.put(""+i, i);
            concurrentHashMap.put(""+i, i);
            synchronizedMap.put(""+i, i);
        }
    }

    @Benchmark
    public void testHashMapGet(){
        hashMap.get("4");
    }

    @Benchmark
    public void testConcurrentHashMapGet(){
        concurrentHashMap.get("4");

    }

    @Benchmark
    public void testSynchronizedMapGet(){
        synchronizedMap.get("4");
    }

    @Benchmark
    public void testHashMapSize(){
        hashMap.size();
    }

    @Benchmark
    public void testConcurrentHashMapSize(){
        concurrentHashMap.size();

    }

    @Benchmark
    public void testSynchronizedMapSize(){
        synchronizedMap.size();
    }


    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder()
                .include(JMHToHashMap.class.getSimpleName())
                .forks(2)
                .measurementIterations(5)//每个方法迭代5次
                .warmupIterations(5)
                .build();
        new Runner(build).run();
    }

}
