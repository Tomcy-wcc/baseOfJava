/*
 * Copyright Bitsoda 2019
 */
package a;

import java.math.BigDecimal;
import java.util.*;


/**
 * RateService 汇率服务, 这个服务计算币种之间汇率. 比如说, 想知道 2个BTC 相当于多少个 ETH. 为笔试目的简化后需要实现两个方法:
 * <p>
 * 1. 提供来源币种, 转换后币种, 返回汇率
 * 2. 提供来源币种数量, 来源币种, 转换后币种, 返回转换后总量
 * <p>
 * marketData存储当前所有可用的汇率信息, 可以认为已经自动实时更新. 比如 { BTC_USDT: 10000 } 意思是 1个BTC 相当于 10000个USDT
 * <p>
 * 请参考测试用例. 注意测试用例必须全部通过
 */
@SuppressWarnings("WeakerAccess")
public class RateService {
    private static Map<String, BigDecimal> marketData = new HashMap<String, BigDecimal>() {{
        this.put("BTC_USDT", BigDecimalBuilder.valueOf(10000.0));
        this.put("ETH_USDT", BigDecimalBuilder.valueOf(200.0));
        this.put("ABC_USDT", BigDecimalBuilder.valueOf(2.0));
        this.put("DEF_ABC", BigDecimalBuilder.valueOf(7.0));
        this.put("CNY_USDT", BigDecimalBuilder.valueOf(0.14));
        // 孤岛币种
        this.put("GBP_SGD", BigDecimalBuilder.valueOf(1.7));
    }};

    public static BigDecimal getRate(String from, String to) {
        Graph graph = new Graph(marketData);
        //获取路径上的汇率
        ArrayList<BigDecimal> allRate = graph.getAllRate(from, to);
        System.out.println(allRate);

        //两个币种汇率算不出来
        if (allRate.size() == 0) {
            return null;
        }

        //将所有的汇率相乘得到两个货币之间的汇率
        BigDecimal result = BigDecimalBuilder.valueOf(1.0);
        for (BigDecimal rate : allRate) {
            result = result.multiply(rate);
        }
        return result;
    }

    public static BigDecimal getRate(BigDecimal amount, String from, String to) {
        //获取汇率
        BigDecimal rate = getRate(from, to);

        // 兑换不了
        if (rate == null) {
            return null;
        }

        // amount_from * rate = amount_to
        return amount.multiply(rate);
    }

    public static void main(String[] args) {
        String from = "CNY";
        String to = "BTC";
        BigDecimal rate = getRate(from, to);
        System.out.println(from + "-->" + to + "之间的汇率为 " + rate);
    }
}
