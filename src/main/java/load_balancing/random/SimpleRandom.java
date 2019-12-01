package load_balancing.random;

import load_balancing.Servers;

import java.util.Random;

/**
 * @Description 简单随机算法
 * @auther wcc
 * @create 2019-12-01 19:32
 */
public class SimpleRandom {

    public static String getServer(){
        int random = new Random().nextInt(Servers.LIST.size());
        return Servers.LIST.get(random);
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++){
            System.out.println(getServer());
        }

    }


}
