package thread;

public class ThreadinterruptTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    //判断该线程是否有中断标识
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("该线程被中断");
                        break;
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("在睡眠的时候被中断了");
                        Thread.currentThread().interrupt();
                        /*boolean interrupted = Thread.interrupted();
                        System.out.println(interrupted);*/
                    }

                }
            }
        });

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断线程
        t1.interrupt();
    }
}
