package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for(int i = 1; i <= 1000; i++){
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 1; i<= 5; i++){
            Thread thread = new Thread(new AddThread());
            thread.start();
            thread.join();
        }

        System.out.println(atomicInteger);
    }
}
