package load_balancing.robin;

import load_balancing.Servers;


/**
 * @Description 简单轮询
 * @auther wcc
 * @create 2019-12-01 20:18
 */
public class SimpleRobin {

    private static int pos = 0;

    public static String getServer(){
        if(pos >= Servers.LIST.size()){
            pos = 0;
        }
        return Servers.LIST.get(pos++);
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++){
            System.out.println(getServer());
        }

    }
}
