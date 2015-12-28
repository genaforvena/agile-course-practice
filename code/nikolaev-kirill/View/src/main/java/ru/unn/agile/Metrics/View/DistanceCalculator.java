package ru.unn.agile.Metrics.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.Metrics.viewmodel.DistanceCalculatorViewModel;
import ru.unn.agile.Metrics.infrastructure.CsvLogger;

public class DistanceCalculator {

    @FXML
    private DistanceCalculatorViewModel viewModel;
    @FXML
    private TextField txtField1stVector;
    @FXML
    private TextField txtField2ndVector;
    @FXML
    private Button btnCalculate;
    @FXML
    private ToggleGroup toggleGroupMetrics;
    @FXML
    private RadioButton radioBtnRhoInf;
    private RadioButton radioBtnSelected;

    @FXML
    public void initialize() {
        viewModel.setLogger(new CsvLogger("./Log.csv"));

        ChangeListener<Boolean> focusChangeListener = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldValue, final Boolean newValue) {
                viewModel.onFocusChange(oldValue, newValue);
            }
        };

        txtField1stVector.textProperty().bindBidirectional(viewModel.firstVectorProperty());
        txtField1stVector.focusedProperty().addListener(focusChangeListener);

        txtField2ndVector.textProperty().bindBidirectional(viewModel.secondVectorProperty());
        txtField2ndVector.focusedProperty().addListener(focusChangeListener);

        radioBtnSelected = radioBtnRhoInf;

        btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });

        toggleGroupMetrics.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(final ObservableValue<? extends Toggle> observable,
                                final Toggle oldValue, final Toggle newValue) {
                String oldMetricName = radioBtnSelected.getText();
                radioBtnSelected = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
                viewModel.setMetric(radioBtnSelected.getText());
                viewModel.onMetricChange(oldMetricName, radioBtnSelected.getText());
            }
        });
    }
}
