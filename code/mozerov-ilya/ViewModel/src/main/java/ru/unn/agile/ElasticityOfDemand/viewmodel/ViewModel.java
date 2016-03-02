package ru.unn.agile.ElasticityOfDemand.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.ElasticityOfDemand.ElasticityOfDemandCalculator;

import java.util.ArrayList;
import java.util.List;

import static ru.unn.agile.ElasticityOfDemand.util.StringToModelUtils.stringToPrice;
import static ru.unn.agile.ElasticityOfDemand.util.StringToModelUtils.stringToQuantity;

public final class ViewModel {
    private final StringProperty oldPrice = new SimpleStringProperty();
    private final StringProperty oldDemandQuantity = new SimpleStringProperty();
    private final StringProperty newPrice = new SimpleStringProperty();
    private final StringProperty newDemandQuantity = new SimpleStringProperty();

    private final BooleanProperty isCalculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    public ViewModel() {
        initFieldValues();

        BooleanBinding isAbleCalculate = new BooleanBinding() {
            {
                super.bind(oldPrice, oldDemandQuantity, newPrice, newDemandQuantity);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        isCalculationDisabled.bind(isAbleCalculate.not());

        final List<StringProperty> inputFields = new ArrayList<>();
        inputFields.add(oldPrice);
        inputFields.add(oldDemandQuantity);
        inputFields.add(newPrice);
        inputFields.add(newDemandQuantity);

        for (StringProperty field : inputFields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
        }
    }

    private void initFieldValues() {
        oldPrice.set("");
        oldDemandQuantity.set("");
        newPrice.set("");
        newDemandQuantity.set("");
        result.set("");
        status.set(Status.WAITING.toString());
    }

    public void calculate() {
        if (isCalculationDisabled.get()) {
            return;
        }

        String resultValue = String.valueOf(
                new ElasticityOfDemandCalculator().calculate(
                        stringToPrice(newPrice.get()),
                        stringToPrice(oldPrice.get()),
                        stringToQuantity(newDemandQuantity.get()),
                        stringToQuantity(oldDemandQuantity.get()))
        );

        result.set(resultValue);

        status.set(Status.SUCCESS.toString());
    }

    public StringProperty oldPriceProperty() {
        return oldPrice;
    }

    public StringProperty oldDemandQuantityProperty() {
        return oldDemandQuantity;
    }

    public StringProperty newPriceProperty() {
        return newPrice;
    }

    public StringProperty newDemandQuantityProperty() {
        return newDemandQuantity;
    }

    public BooleanProperty isCalculationDisabledProperty() {
        return isCalculationDisabled;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty statusProperty() {
        return status;
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;

        boolean isAnyInputEmpty = oldPrice.get().isEmpty() || oldDemandQuantity.get().isEmpty()
                || newPrice.get().isEmpty() || newDemandQuantity.get().isEmpty();

        if (isAnyInputEmpty) {
            return Status.WAITING;
        }

        if (!isInputValid()) {
            return Status.BAD_FORMAT;
        }

        return inputStatus;
    }

    private boolean isInputValid() {
        try {
            stringToPrice(newPrice.get());
            stringToPrice(oldPrice.get());
            stringToQuantity(newDemandQuantity.get());
            stringToQuantity(oldDemandQuantity.get());
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    public enum Status {
        WAITING("Please provide input data"),
        READY("Press 'Calculate' or Enter"),
        BAD_FORMAT("Bad format"),
        SUCCESS("Success");

        private final String name;

        Status(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
