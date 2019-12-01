package load_balancing.random;

import load_balancing.Servers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


/**
 * @Description 加权随机算法
 * @auther wcc
 * @create 2019-12-01 19:31
 */
public class WeightedRandom {
    /**
     * 算法实现一
     * 思路：把权值看作当前服务器的个数
     * @return
     */
    public static String getServer1(){

        // 将所有的服务器添加到集合里
        ArrayList<String> servers = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : Servers.serverIps.entrySet()){
            String ip = entry.getKey();
            Integer weight = entry.getValue();
            for (int i = 1; i <= weight; i++){
                servers.add(ip);
            }
        }

        // 在集合中随机选出一个服务器
        int random = new Random().nextInt(servers.size());

        return servers.get(random);
    }

    /**
     * 算法实现二
     * 思路：将权值看作线段中的宽度
     * @return
     */
    public static String getServer2(){

        // 算出随机范围  随机范围 = 所有服务器的权值之和
        int bound = 0;

        for (Map.Entry<String, Integer> entry : Servers.serverIps.entrySet()){
            bound += entry.getValue();
        }

        // 产生随机数
        int pos = new Random().nextInt(bound);

        // 计算随机数落在那个位置上，返回相应的服务器
        for (Map.Entry<String, Integer> entry : Servers.serverIps.entrySet()){

            if (pos < entry.getValue()){
                return entry.getKey();
            }

            pos -= entry.getValue();
        }

        return "";
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++){
            System.out.println(getServer2());
        }

    }
}
