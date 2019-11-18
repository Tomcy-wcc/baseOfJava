package a;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalBuilder {
    public static final int SCALE = 8;

    public static BigDecimal valueOf(double d) {
        return BigDecimal.valueOf(d).setScale(SCALE, RoundingMode.HALF_EVEN);
    }
}
