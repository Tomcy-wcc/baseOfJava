package thread;

/**
 * @Description
 * @auther wcc
 * @create 2019-09-16 9:31
 */
public class VolatileDemo01 {

    public static volatile boolean flag = false;//volatile保证线程之间的可见性

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start");
                while (!flag){
                    //空循环，等待另一个线程改变循环条件来停止空循环
                }
                System.out.println("end");
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("change flag");
                flag = true;
                System.out.println("change flag success");
            }
        }).start();

    }

}
