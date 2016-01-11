package ru.unn.agile.CurrencyConverter.Model;

public enum UnitCurrency {

    DOLLAR(1),
    EURO(1.16),
    RUBLE(0.016),
    POUND(1.59);

    static final double HUNDREDTH_PATH  = 100;
    private final double someCurrencyToDollar;

    UnitCurrency(final double someCurrencyToDollar) {
        this.someCurrencyToDollar = someCurrencyToDollar;
    }

        public Currency convertCurrency(final Currency inputCurrency,
                                        final UnitCurrency unitOutputCurrency) {
            Currency outputCurrency = new Currency(0, UnitCurrency.DOLLAR);
            if (inputCurrency == null) {
                throw new NullPointerException("Please, input value and choose currency.");
            }
            if (inputCurrency.getValue() < 0) {
                throw new IllegalArgumentException("Please, use positive value.");
            }
            double outputCurrencyBeforeRound = inputCurrency.getUnit().someCurrencyToDollar
                    * inputCurrency.getValue() / unitOutputCurrency.someCurrencyToDollar;
            double valueOutputCurrencyAfterRound = Math.round(outputCurrencyBeforeRound
                    * HUNDREDTH_PATH) / HUNDREDTH_PATH;
            outputCurrency.setValue(valueOutputCurrencyAfterRound);
            outputCurrency.setUnit(unitOutputCurrency);
            return outputCurrency;

        }
}
