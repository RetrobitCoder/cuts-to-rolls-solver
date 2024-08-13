package io.github.retrobitcoder;

import java.math.BigDecimal;
import java.math.MathContext;

public class ConversionUtility {
    /**
     * Convert a double that comprises feet with the decimal part being inches to just inches
     * @param feet feet.inches
     * @return int of only inches
     */
    public static int feetToInches(double feetWithInches) {
        BigDecimal measurement = BigDecimal.valueOf(feetWithInches);

        int feet = measurement.intValue();
        int inches = measurement.subtract(new BigDecimal(feet)).scaleByPowerOfTen(2).round(new MathContext(2)).intValue();

        inches += (feet * 12);

        return inches;
    }
}
