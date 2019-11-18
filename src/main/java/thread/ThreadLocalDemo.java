package thread;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 里面重要的两个方法get()和set()
 *
 * 每个Thread里面都有ThreadLocalMap对象保存着ThreadLocal（资源副本），这样就线程安全了
 *
 * get():
 *      //Thread里面有个 ThreadLocalMap（ThreadLocal集合）可以理解为HashMap存放键值对的key为ThreadLocal本身，value为资源对象
 *     public T get() {
 *         Thread t = Thread.currentThread();
 *         //根据当前线程获取ThreadLocalMap
 *         ThreadLocalMap map = getMap(t);
 *         if (map != null) {
 *             ThreadLocalMap.Entry e = map.getEntry(this);
 *             if (e != null) {
 *                 T result = (T)e.value;
 *                 //取出值
 *                 return result;
 *             }
 *         }
 *         return setInitialValue();
 *     }
 * set():
 *     public void set(T value) {
 *         Thread t = Thread.currentThread();
 *         //根据当前线程获取ThreadLocalMap
 *         ThreadLocalMap map = getMap(t);
 *         //设置值
 *         if (map != null)
 *             map.set(this, value);
 *         else
 *             createMap(t, value);
 *     }
 *
 */

public class ThreadLocalDemo {

    //共享simpleDateFormat 线程不安全
    //static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //使每个线程都有SimpleDateFormat，这样就线程安全了
    static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal(){
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString()+" is gc");
        }
    };

    static class ParseDate implements Runnable{

        @Override
        public void run() {
            try {
                if(dateFormatThreadLocal.get() == null){
                    dateFormatThreadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString()+" is gc");
                        }
                    });
                }
                SimpleDateFormat simpleDateFormat = dateFormatThreadLocal.get();
                System.out.println(Thread.currentThread().getName()+"---->"+ System.identityHashCode(simpleDateFormat));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 1; i<= 10; i++){
            executorService.execute(new ParseDate());
        }
        Thread.sleep(1000);
        dateFormatThreadLocal = null;
        System.gc();
        executorService.shutdown();*/
        System.out.println(Integer.toBinaryString(-536870909));
        System.out.println(Integer.toBinaryString(-536870911));

    }
}
