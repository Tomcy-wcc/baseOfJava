package load_balancing;

/**
 * @Description 服务器信息
 * @auther wcc
 * @create 2019-12-01 20:27
 */
public class ServerInfo {

    private  String ip;
    private Integer weight;
    //当前权值
    private Integer currentWeight;

    public ServerInfo(String ip, Integer weight, Integer currentWeight) {
        this.ip = ip;
        this.weight = weight;
        this.currentWeight = currentWeight;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(Integer currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "ip='" + ip + '\'' +
                ", weight=" + weight +
                ", currentWeight=" + currentWeight +
                '}';
    }
}
