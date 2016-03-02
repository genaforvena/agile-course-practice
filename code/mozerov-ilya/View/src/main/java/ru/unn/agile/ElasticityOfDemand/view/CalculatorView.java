package ru.unn.agile.ElasticityOfDemand.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.ElasticityOfDemand.viewmodel.ViewModel;

public class CalculatorView {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField txtOldPrice;
    @FXML
    private TextField txtOldDemandQuantity;
    @FXML
    private TextField txtNewPrice;
    @FXML
    private TextField txtNewDemandQuantity;
    @FXML
    private Button btnCalc;

    @FXML
    void initialize() {
        txtOldPrice.textProperty().bindBidirectional(viewModel.oldPriceProperty());
        txtOldDemandQuantity.textProperty()
                .bindBidirectional(viewModel.oldDemandQuantityProperty());
        txtNewPrice.textProperty().bindBidirectional(viewModel.newPriceProperty());
        txtNewDemandQuantity.textProperty()
                .bindBidirectional(viewModel.newDemandQuantityProperty());

        btnCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.calculate();
            }
        });
    }
}
