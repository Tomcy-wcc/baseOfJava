package thread;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimitDemo {

    //限流器，每秒产生4个令牌
    static RateLimiter rateLimiter = RateLimiter.create(2);

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++){
            //消耗一个令牌，如果没有令牌，则等待
            //rateLimiter.acquire();
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!rateLimiter.tryAcquire()){
                continue;
            }
            //处理一个任务
            new Thread(new Task()).start();
        }
    }
}
