package thread;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    public static AtomicReference<Integer> money = new AtomicReference<>();


    //充钱服务
    static class AddMoneyTask implements Runnable{

        @Override
        public void run() {
            while (true){
                while (true){
                    //获取当前的money
                    Integer currentMoney = money.get();
                    if(currentMoney < 20){
                        //冲20
                        if(money.compareAndSet(currentMoney, currentMoney+20)){
                            System.out.println("充钱成功，你的余额为:"+money.get());
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
                while (true){
                    Integer currentMoney = money.get();
                    if(currentMoney < 10){
                        System.out.println("你的余额不足10元");
                        break;
                    }else {
                        if(money.compareAndSet(currentMoney, currentMoney-10)){
                            System.out.println("花费成功，你的余额为"+money.get());
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
        money.set(10);
        new Thread(new AddMoneyTask()).start();
        new Thread(new SpendMoneyTask()).start();
    }


}
