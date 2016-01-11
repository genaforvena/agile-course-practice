package ru.unn.agile.PercentAccretion.viewmodel;

import ru.unn.agile.PercentAccretion.Model.PercentAccretionFactory;
import ru.unn.agile.PercentAccretion.Model.PercentAccretion;
import ru.unn.agile.PercentAccretion.Model.PercentData;

import java.util.List;

public class PercentAccretionViewModel {
    public enum PercentAccretionStatus {
        FIELD_IS_EMPTY("Empty fields!"),
        INCORRECT_VALUES("Wrong values!"),
        CLICK_BUTTON("Please click\"Calculate\"!"),
        SUCCESS("Success"),
        EXIT("Exit");

        private String statusMessage;

        PercentAccretionStatus(final String newStatusMessage) {
            this.statusMessage = newStatusMessage;
        }

        public String getMessage() {
            return statusMessage;
        }
    }

    public enum LogMessages {
        PARAMETERS_WERE_UPDATED("Parameters were updated to: "),
        PERCENT_TYPE_WAS_CHANGED("Percent type was changed: "),
        CALCULATE_WAS_PRESSED("Calculate: ");

        private String message;

        LogMessages(final String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }

    private boolean calculateButtonEnabled;
    private boolean initialSumIsCorrect;
    private boolean percentRateIsCorrect;
    private boolean countOfYearsIsCorrect;
    private boolean areParametersChanged;
    private PercentAccretionFactory.AccretionType operation;
    private final PercentData data;
    private String statusMessage;
    private String resultSum;
    private static final String EMPTY_STRING = "";
    private IPercentAccretionLogger logger;

    public String getResultSum() {
        return resultSum;
    }

    public boolean isCalculateButtonEnabled() {
        if (checkFieldsHaveRightValues()) {
            calculateButtonEnabled = true;
            if (EMPTY_STRING.equals(resultSum)) {
                statusMessage = PercentAccretionStatus.CLICK_BUTTON.getMessage();
            } else {
                statusMessage = PercentAccretionStatus.SUCCESS.getMessage();
            }
        } else {
            calculateButtonEnabled = false;
        }
        return calculateButtonEnabled;
    }

    public PercentData getData() {
        return data;
    }

    public void setPercentType(final String value) {
        if (!this.operation.toString().equals(value)) {
            if (PercentAccretionFactory.AccretionType.
                    SIMPLE_PERCENT_SUM.toString().equals(value)) {
                operation = PercentAccretionFactory.AccretionType.SIMPLE_PERCENT_SUM;
            }
            if (PercentAccretionFactory.AccretionType.
                    COMPLEX_PERCENT_SUM.toString().equals(value)) {
                operation = PercentAccretionFactory.AccretionType.COMPLEX_PERCENT_SUM;
            }
            logger.log(LogMessages.PERCENT_TYPE_WAS_CHANGED.toString() + operation.toString());
            resultSum = EMPTY_STRING;
        }
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void focusLost() {
        logParameters();
    }

    public void setInitialSum(final String value) {
        resultSum = EMPTY_STRING;
        if (checkValue(value)) {
            initialSumIsCorrect = false;
            return;
        }
        if (!String.valueOf(this.data.getInitialSum()).equals(value)) {
            data.setInitialSum(Double.valueOf(value));
            initialSumIsCorrect = true;
            clearStatusMessage();
            areParametersChanged = true;
        }
    }

    public void setPercentRate(final String value) {
        resultSum = EMPTY_STRING;
        if (checkValue(value)) {
            percentRateIsCorrect = false;
            return;
        }
        if (!String.valueOf(this.data.getPercentRate()).equals(value)) {
            data.setPercentRate(Double.valueOf(value));
            percentRateIsCorrect = true;
            clearStatusMessage();
            areParametersChanged = true;
        }
    }

    public void setCountOfYears(final String value) {
        resultSum = EMPTY_STRING;
        if (checkValue(value)) {
            countOfYearsIsCorrect = false;
            return;
        }
        if (!String.valueOf(this.data.getCountOfYears()).equals(value)) {
            data.setCountOfYears(Integer.valueOf(value));
            countOfYearsIsCorrect = true;
            clearStatusMessage();
            areParametersChanged = true;
        }
    }

    public void calculateResultSum() {
        PercentAccretionFactory percentAccretionFactory = new PercentAccretionFactory();
        PercentAccretion percentCounter = percentAccretionFactory.create(operation);
        resultSum = String.valueOf(percentCounter.calculate(data));
        logger.log(calculatingMessage());
    }

    public PercentAccretionViewModel() {
        data = new PercentData();
        calculateButtonEnabled = false;
        initialSumIsCorrect = false;
        percentRateIsCorrect = false;
        countOfYearsIsCorrect = false;
        operation = PercentAccretionFactory.AccretionType.SIMPLE_PERCENT_SUM;
        statusMessage = PercentAccretionStatus.FIELD_IS_EMPTY.getMessage();
    }

    public PercentAccretionViewModel(final IPercentAccretionLogger logger) {
        this();
        if (logger == null) {
            throw new IllegalArgumentException("Logger can't be null");
        }
        this.logger = logger;
    }

    public List<String> getLog() {
        return logger.getLog();
    }

    private boolean checkValue(final String value) {
        if (EMPTY_STRING.equals(value)) {
            statusMessage = PercentAccretionStatus.FIELD_IS_EMPTY.getMessage();
            return true;
        }
        if (!value.matches("\\d+" + '.' + "\\d+") && !value.matches("\\d+")) {
            statusMessage = PercentAccretionStatus.INCORRECT_VALUES.getMessage();
            return true;
        }
        return false;
    }

    private void logParameters() {
        if (areParametersChanged) {
            logger.log(editingFinishedLogMessage());
            areParametersChanged = false;
        }
    }

    private String editingFinishedLogMessage() {
        String message = LogMessages.PARAMETERS_WERE_UPDATED.toString()
                + ": ["
                + data.getInitialSum() + "; "
                + data.getPercentRate() + "; "
                + data.getCountOfYears() + "]";

        return message;
    }

    private String calculatingMessage() {
        String message = LogMessages.CALCULATE_WAS_PRESSED.toString()
                + "Initial Sum: " + data.getInitialSum() + ";"
                + "Percent Rate: " + data.getPercentRate() + ";"
                + "Count of years: " + data.getCountOfYears() + ";"
                + "Operation: " + operation.toString();

        return message;
    }

    private boolean checkFieldsHaveRightValues() {
        return initialSumIsCorrect && percentRateIsCorrect && countOfYearsIsCorrect;
    }

    private void clearStatusMessage() {
        if (checkFieldsHaveRightValues()) {
            statusMessage = PercentAccretionStatus.SUCCESS.getMessage();
        }
    }
}
