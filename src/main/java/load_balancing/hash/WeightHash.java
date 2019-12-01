package load_balancing.hash;

import load_balancing.Servers;

import java.util.*;

/**
 * @Description 哈希环算法
 * @auther wcc
 * @create 2019-12-01 22:49
 */
public class WeightHash {

    private static final TreeMap<Integer, String> hashLoop = new TreeMap<>();

    static {
        for(Map.Entry<String, Integer> entry : Servers.serverIps.entrySet()){
            for (int i = 1; i <= entry.getValue(); i++){
                hashLoop.put(getHash(entry.getKey()), entry.getKey());
            }
        }
    }

    /**
     * 计算Hash值, 使用FNV1_32_HASH算法
     * @param str
     * @return
     */
    public static int getHash(String str) {
        final int p = 16777619;
        int hash = (int)2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash =( hash ^ str.charAt(i) ) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    /**
     *
     * @param clientInfo
     * @return
     */
    public static String getServer(String clientInfo){

        int hashcode = getHash(clientInfo);

        SortedMap<Integer, String> sortedMap = hashLoop.tailMap(hashcode);

        // 如果hashcode在最后的节点和第一个节点之间
        if (sortedMap == null){
            return hashLoop.firstEntry().getValue();
        }

        Integer integer = sortedMap.firstKey();

        return hashLoop.get(integer);


    }

    public static void main(String[] args) {

        ArrayList<String> clientInfo = new ArrayList<>();

        Random random = new Random();
        for (int i = 1; i < 100; i++){
            clientInfo.add(random.nextInt(1000000)+"");
        }


        for (int i = 0; i < clientInfo.size(); i++){
            System.out.println(getServer(clientInfo.get(i)));
        }
    }


}
