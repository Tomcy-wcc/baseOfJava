package thread;

public class ThreadPriorityDemo {

    static class HighPriority extends Thread{
        static int count = 0;
        @Override
        public void run() {
            while (true){
                synchronized (ThreadPriorityDemo.class){
                    count++;
                    if(count > 10000){
                        System.out.println("HighPriority");
                        break;
                    }
                }
            }
        }
    }
    static class LowPriority extends Thread{
        static int count = 0;
        @Override
        public void run() {
            while (true){
                synchronized (ThreadPriorityDemo.class){
                    count++;
                    if(count > 10000){
                        System.out.println("LowPriority");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HighPriority highPriority = new HighPriority();
        highPriority.setPriority(8);
        LowPriority lowPriority = new LowPriority();
        lowPriority.setPriority(5);
        lowPriority.start();
        highPriority.start();
    }
}
