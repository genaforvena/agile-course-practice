package ru.unn.agile.ElasticityOfDemand.util;

import java.math.BigDecimal;

/**
 * Created by imozerov on 02.03.16.
 */
public final class StringToModelUtils {
    private StringToModelUtils() {

    }

    /**
     * Converts string to price BigDecimal value.
     *
     * @param stringToConvert String that needs to be converted.
     * @return BigDecimal
     *
     * @throws NumberFormatException if fails to convert input string
     * to BigDecimal.
     */
    public static BigDecimal stringToPrice(final String stringToConvert) {
        if (stringToConvert == null || stringToConvert.isEmpty()) {
            throw new NumberFormatException(
                    String.format("Cannot parse string %s to price! String is empty",
                            stringToConvert));
        }

        BigDecimal price = new BigDecimal(stringToConvert);

        boolean isPriceNegative = price.compareTo(BigDecimal.ZERO) < 0;
        if (isPriceNegative) {
            throw new NumberFormatException(
                    String.format(
                            "Cannot parse string %s to price! Cannot be less or equal to 0",
                            stringToConvert));
        }
        return price;
    }

    /**
     * Converts string to quantity Long value.
     *
     * @param stringToConvert String that needs to be converted.
     * @return long value of the string.
     *
     * @throws NumberFormatException if fails to convert input string
     * to long.
     */
    public static long stringToQuantity(final String stringToConvert) {
        long quantity = Long.parseLong(stringToConvert);

        if (quantity < 0) {
            throw new NumberFormatException("Quantity cannot be negative!");
        }

        return quantity;
    }
}
