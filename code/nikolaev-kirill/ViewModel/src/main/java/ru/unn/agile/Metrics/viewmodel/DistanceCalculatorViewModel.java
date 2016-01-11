package ru.unn.agile.Metrics.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.Metrics.model.DistanceCalculator;
import ru.unn.agile.Metrics.model.Metric;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalculatorViewModel {
    public static final String HELP_MESSAGE = "Provide input vectors: decimal or integer numbers "
            + "separated by single whitespaces";
    public static final String CALCULATE_PRESSED = "Calculate button has been pressed.";
    public static final String METRIC_CHANGED = "Metric has been changed to: ";
    public static final String INPUT_UPDATED = "Input vector has been updated.";
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty firstVector = new SimpleStringProperty();
    private final StringProperty secondVector = new SimpleStringProperty();
    private final StringProperty statusMessage = new SimpleStringProperty();
    private final StringProperty logText = new SimpleStringProperty();
    private final BooleanProperty calculateButtonDisabled = new SimpleBooleanProperty();
    private final StringProperty metricName = new SimpleStringProperty();
    private final List<ValueChangeListener> valueChangeListeners = new ArrayList<>();
    private final DistanceCalculator calculator = new DistanceCalculator();
    private ILogger logger;

    public DistanceCalculatorViewModel() {
        initialize();
    }

    public DistanceCalculatorViewModel(final ILogger logger) {
        this.logger = logger;
        initialize();
    }

    public StringProperty firstVectorProperty() {
        return firstVector;
    }
    public void setFirstVector(final String vectorString) {
        firstVector.set(vectorString);
    }

    public StringProperty secondVectorProperty() {
        return secondVector;
    }
    public void setSecondVector(final String vectorString) {
        secondVector.set(vectorString);
    }

    public void setMetric(final String metricString) {
        metricName.set(metricString);
    }
    public String getMetricName() {
        return metricName.get();
    }

    public StringProperty statusMessageProperty() {
        return statusMessage;
    }
    public String getStatusMessage() {
        return statusMessage.get();
    }

    public StringProperty resultProperty() {
        return result;
    }
    public String getResult() {
        return result.get();
    }

    public StringProperty logTextProperty() {
        return logText;
    }
    public String getLogText() {
        return logText.get();
    }

    public BooleanProperty calculateButtonDisabledProperty() {
        return calculateButtonDisabled;
    }
    public boolean isCalculateButtonDisabled() {
        return calculateButtonDisabled.get();
    }

    public void setLogger(final ILogger logger) {
        this.logger = logger;
    }

    public List<String> getLog() {
        return logger.get();
    }

    public String getLastLogMessage() {
        List<String> log = getLog();
        return log.get(log.size() - 1);
    }

    public String getInputStatus() {
        String firstVector = this.firstVector.get();
        String secondVector = this.secondVector.get();
        boolean isBadInputFormat = !checkVectorString(firstVector)
                || !checkVectorString(secondVector);
        if (isBadInputFormat) {
            calculateButtonDisabled.set(true);
            return "Bad vector format";
        } else {
            if (firstVector.isEmpty() || secondVector.isEmpty()) {
                calculateButtonDisabled.set(true);
                return HELP_MESSAGE;
            }
            boolean haveDifferentSize = firstVector.split(" ").length
                    != secondVector.split(" ").length;
            if (haveDifferentSize) {
                calculateButtonDisabled.set(true);
                return "Vectors have different size";
            } else {
                calculateButtonDisabled.set(false);
                return "";
            }
        }
    }

    public void calculate() {
        if (calculateButtonDisabled.get()) {
            throw new IllegalStateException("Calculation is disabled: Empty or incorrect input");
        }
        float[] firstVector = parseVector(this.firstVector.get());
        float[] secondVector = parseVector(this.secondVector.get());
        Metric metric = parseMetric(metricName.get());
        result.set(Float.toString(calculator.calculateDistance(firstVector, secondVector,
                metric)));
        logger.add(CALCULATE_PRESSED + " Arguments: [" + firstVectorProperty().get() + "]; ["
                + secondVectorProperty().get() + "] Metric: " + metricName.get());
        updateLogText();
    }

    public void onMetricChange(final String oldValue, final String newValue) {
        if (oldValue.equals(newValue)) {
            return;
        }
        logger.add(METRIC_CHANGED + newValue);
        updateLogText();
    }

    public void onFocusChange(final Boolean oldValue, final Boolean newValue) {
        if (!oldValue && newValue) {
            return;
        }
        for (ValueChangeListener listener: valueChangeListeners) {
            if (listener.valueIsChanged()) {
                logger.add(INPUT_UPDATED + " Arguments: ["
                        + firstVectorProperty().get() + "]; ["
                        + secondVectorProperty().get() + "]");
                listener.savePrevValue();
                updateLogText();
            }
        }
    }

    private void initialize() {
        result.set("");
        firstVector.set("");
        secondVector.set("");
        logText.set("");
        statusMessage.set(HELP_MESSAGE);
        calculateButtonDisabled.set(true);
        metricName.set("RHO INF");

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(firstVector);
                add(secondVector);
            }
        };

        for (StringProperty field: fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangeListeners.add(listener);
        }
    }

    private void updateLogText() {
        List<String> fullLog = logger.get();
        String text = "";
        for (String message: fullLog) {
            text += message + '\n';
        }
        logText.set(text);
    }

    private float[] parseVector(final String rawVector) {
        float[] vector;
        String[] parts = rawVector.split(" ");
        vector = new float[parts.length];
        for (int i = 0; i < parts.length; i++) {
            float component = Float.parseFloat(parts[i]);
            vector[i] = component;
        }
        return vector;
    }

    private boolean checkVectorString(final String vectorString) {
        String pattern = "((-?\\d+\\.\\d+|-?\\d+)*\\s)*(-?\\d+\\.\\d+|-?\\d+)";
        return vectorString.isEmpty() || vectorString.matches(pattern);
    }

    private Metric parseMetric(final String metricString) {
        switch (metricString) {
            case "RHO INF":
                return Metric.RHO_INF;
            case "RHO ONE":
                return Metric.RHO_ONE;
            case "RHO TWO":
                return Metric.RHO_TWO;
            case "RHO THREE":
                return Metric.RHO_THREE;
            case "RHO FOUR":
                return Metric.RHO_FOUR;
            default:
                throw new IllegalArgumentException("Invalid metric name");
        }
    }

    private class ValueChangeListener implements ChangeListener<String> {
        private String prevValue = "";
        private String currValue = "";

        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            statusMessage.set(getInputStatus());
            currValue = newValue;
        }

        public boolean valueIsChanged() {
            return !prevValue.equals(currValue);
        }

        public void savePrevValue() {
            prevValue = currValue;
        }
    }
}
