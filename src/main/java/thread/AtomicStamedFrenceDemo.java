package thread;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStamedFrenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);
    //充钱服务
    static class AddMoneyTask implements Runnable{

        @Override
        public void run() {
            int timestamp = money.getStamp();
            while (true){
                while (true){
                    //获取当前的money
                    Integer currentMoney = money.getReference();
                    if(currentMoney < 20){
                        //冲20
                        if(money.compareAndSet(currentMoney, currentMoney+20, timestamp, timestamp+1)){
                            System.out.println("充钱成功，你的余额为:"+money.getReference());
                            break;
                        }
                    }else {
                        System.out.println("余额大于20元");
                        break;
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    //花钱服务
    static class SpendMoneyTask implements Runnable{

        @Override
        public void run() {

            while (true){
                int timestamp = money.getStamp();
                while (true){
                    Integer currentMoney = money.getReference();
                    if(currentMoney < 10){
                        System.out.println("你的余额不足10元");
                        break;
                    }else {
                        if(money.compareAndSet(currentMoney, currentMoney-10, timestamp, timestamp+1)){
                            System.out.println("花费成功，你的余额为"+money.getReference());
                            break;
                        }
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new AddMoneyTask()).start();
        new Thread(new SpendMoneyTask()).start();
    }
}
