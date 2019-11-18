package a;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

/**
 * @Description
 * @auther wcc
 * @create 2019-11-12 0:25
 */
public class Graph {
    private String[] vexs; //顶点(币种)
    private BigDecimal[][] arcs; //带权（汇率）有向图 币种相同，汇率为1

    private int[] visited;

    private Map<String, BigDecimal> marketData;

    public Graph(Map<String, BigDecimal> marketData) {
        this.marketData = marketData;
        initVexs();
        initArcs();
    }


    public void initVisited(){
        visited = new int[vexs.length];
    }



    /**
     * 获取所有币种
     * @return
     */
    public void initVexs(){
        HashSet<String> vexSet = new HashSet<>();
        for(Map.Entry<String, BigDecimal> entry : marketData.entrySet()){
            String[] split = entry.getKey().split("_");
            vexSet.add(split[0]);
            vexSet.add(split[1]);
        }
        this.vexs = new String[vexSet.size()];
        vexSet.toArray(this.vexs);
    }


    public int getIndex(String key){
        for(int i = 0; i < vexs.length; i++){
            if(vexs[i].equals(key)){
                return i;
            }
        }
        return -1;
    }


    /**
     * 根据币种之间的汇率生成带权的邻接矩阵
     * @return
     */
    public BigDecimal[][] initArcs(){
        this.arcs = new BigDecimal[vexs.length][vexs.length];
        //将币种相同的汇率设置为1.0
        for (int i = 0; i < vexs.length; i++){
            arcs[i][i] = BigDecimalBuilder.valueOf(1.0);
        }
        for(Map.Entry<String, BigDecimal> entry : marketData.entrySet()){
            String[] split = entry.getKey().split("_");
            //获取split[0]和split[1]的下标，设置这两个币种之间的汇率
            int x = getIndex(split[0]);
            int y = getIndex(split[1]);
            arcs[x][y] = entry.getValue();
            //y -- > x 汇率为倒数
            arcs[y][x] = BigDecimalBuilder.valueOf(1.0).divide(entry.getValue(), BigDecimal.ROUND_DOWN);
        }
        return arcs;
    }

    /**
     * 获取String from, String to这条路径上的所有汇率
     * @param from
     * @param to
     * @return
     */
    public ArrayList<BigDecimal> getAllRate(String from, String to){
        initVisited();
        Stack<Integer> path = new Stack<>();
        Stack<Integer> resultPath = new Stack<>();
        int fromIndex = getIndex(from);
        int toIndex = getIndex(to);
        DFS(fromIndex, toIndex, path, resultPath);
        //System.out.println(path);
        ArrayList<BigDecimal> allRate = new ArrayList<>();
        for(int i = 1; i < resultPath.size(); i++){
            allRate.add(arcs[resultPath.get(i-1)][resultPath.get(i)]);
        }
        return allRate;
    }

    /**
     * 深搜
     * @param i 开始节点下标
     * @param j 结束节点下标
     * @param path
     */
    public void DFS(int i, int j, Stack<Integer> path, Stack<Integer> resultPath){
        visited[i] = 1;
        path.push(i);
        //找到，保存结果
        if (i == j){
            for(Integer vex : path){
                resultPath.add(vex);
            }
            return;
        }
        for(int k = 0; k < vexs.length; k++){
            if(visited[k] == 0 && arcs[i][k] != null){
                DFS(k, j, path, resultPath);
            }
        }
        path.pop();
        visited[i] = 0;
    }


    public void printArcs(){
        for (int i = 0; i < arcs.length; i++){
            for(int j = 0; j < arcs[i].length; j++){
                System.out.print(arcs[i][j]+"\t");
            }
            System.out.println();
        }
    }


    public String[] getVexs() {
        return vexs;
    }

    public void setVexs(String[] vexs) {
        this.vexs = vexs;
    }

    public BigDecimal[][] getArcs() {
        return arcs;
    }

    public void setArcs(BigDecimal[][] arcs) {
        this.arcs = arcs;
    }

    public Map<String, BigDecimal> getMarketData() {
        return marketData;
    }

    public void setMarketData(Map<String, BigDecimal> marketData) {
        this.marketData = marketData;
    }
}
