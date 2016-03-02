package ru.unn.agile.ElasticityOfDemand.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.ElasticityOfDemand.log.Logger;
import ru.unn.agile.ElasticityOfDemand.util.NullLogger;
import ru.unn.agile.ElasticityOfDemand.viewmodel.ViewModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CalculatorView {
    public static final String LOG_FILE = "./TxtLogger_Tests-lab3.log";

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

    private ViewModel viewModel;

    @FXML
    void initialize() {
        try {
            viewModel = new ViewModel(new Logger(new BufferedWriter(new FileWriter(LOG_FILE))));
        } catch (IOException e) {
            System.out.println("Unable to setup logger. Check file permissions!");
            e.printStackTrace();
            viewModel = new ViewModel(new NullLogger());
        }
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
