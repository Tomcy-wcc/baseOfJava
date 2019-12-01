package load_balancing.robin;

import load_balancing.ServerInfo;
import load_balancing.Servers;

import java.util.Map;

/**
 * @Description 加权轮询算法
 * @auther wcc
 * @create 2019-12-01 20:26
 */
public class WeightedRobin {

    private static int pos = 0;

    /**
     * 平滑加权轮询算法
     *
     * 每次都返回当前权值最大的服务器IP
     * @return
     */
    public static String getServer1(){

        //计算出权值和
        int totalWeight = 0;
        for (ServerInfo serverInfo : Servers.SERVER_INFO_LIST){
            totalWeight += serverInfo.getWeight();
        }

        // 设置currentWeight
        for (ServerInfo serverInfo : Servers.SERVER_INFO_LIST){
            serverInfo.setCurrentWeight(serverInfo.getCurrentWeight()+serverInfo.getWeight());
        }

        // 得到当前权值最大的服务器IP
        ServerInfo maxServer = null;
        for (ServerInfo serverInfo : Servers.SERVER_INFO_LIST){
            if (maxServer == null){
                maxServer = serverInfo;
            }

            if(serverInfo.getCurrentWeight() > maxServer.getCurrentWeight()){
                maxServer = serverInfo;
            }
        }

        // 设置最大值的currentWeight
        maxServer.setCurrentWeight(maxServer.getCurrentWeight()-totalWeight);
        System.out.println(Servers.SERVER_INFO_LIST);

        return maxServer.getIp();
    }

    /**
     * 简单加权轮询
     * @return
     */
    public static String getServer2(){
        // 所有服务器的权值之和
        int totalWeight = 0;

        for (Map.Entry<String, Integer> entry : Servers.serverIps.entrySet()){
            totalWeight += entry.getValue();
        }

        if(pos >= totalWeight){
            pos = 0;
        }

        // 计算pos落在那个位置上，返回相应的服务器
        int temp = pos;

        for (Map.Entry<String, Integer> entry : Servers.serverIps.entrySet()){

            if (temp < entry.getValue()){
                pos++;
                return entry.getKey();
            }

            temp -= entry.getValue();
        }

        return "";
    }



    public static void main(String[] args) {

        for (int i = 1; i <= 30; i++){
            System.out.println(getServer2());
        }

    }
}
