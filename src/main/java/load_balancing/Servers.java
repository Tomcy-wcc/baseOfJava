package load_balancing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther wcc
 * @create 2019-12-01 19:33
 */
public class Servers {

    public static final List<String> LIST = Arrays.asList(
            "192.168.1.1",
            "192.168.1.2",
            "192.168.1.3",
            "192.168.1.4",
            "192.168.1.5",
            "192.168.1.6"
    );

    public static final List<ServerInfo> SERVER_INFO_LIST = Arrays.asList(
            new ServerInfo("A", 5, 0),
            new ServerInfo("B", 1, 0),
            new ServerInfo("C", 1, 0)
//            new ServerInfo("192.168.1.4", 7, 0),
//            new ServerInfo("192.168.1.5", 6, 0),
//            new ServerInfo("192.168.1.6", 3, 0)
    );

    public static final Map<String, Integer> serverIps = new HashMap<>();

    static {
        serverIps.put("192.168.1.1", 2);
        serverIps.put("192.168.1.2", 1);
        serverIps.put("192.168.1.3", 5);
        serverIps.put("192.168.1.4", 7);
        serverIps.put("192.168.1.5", 6);
        serverIps.put("192.168.1.6", 3);
    }

}
