package ru.unn.agile.QuadraticEquationSolver.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.unn.agile.QuadraticEquation.infrastructure.QuadraticEquationLogger;
import ru.unn.agile.QuadraticEquationSolver.viewmodel.ViewModel;

public class EquationsSolver {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField textFieldCoeffA;
    @FXML
    private TextField textFieldCoeffB;
    @FXML
    private TextField textFieldCoeffC;
    @FXML
    private Button buttonSolveEquation;

    @FXML
    void initialize() {
        viewModel.createLogger(new QuadraticEquationLogger("./QuadraticEquationLogger.txt"));

        final ChangeListener<Boolean> focusChangedObserver = new ChangeListener<Boolean>() {
            @Override
            public void changed(final ObservableValue<? extends Boolean> observable,
                                final Boolean oldState, final Boolean newState) {
                viewModel.onFocusChanged(oldState, newState);
            }
        };

        textFieldCoeffA.textProperty().bindBidirectional(viewModel.coeffAProperty());
        textFieldCoeffA.focusedProperty().addListener(focusChangedObserver);

        textFieldCoeffB.textProperty().bindBidirectional(viewModel.coeffBProperty());
        textFieldCoeffB.focusedProperty().addListener(focusChangedObserver);

        textFieldCoeffC.textProperty().bindBidirectional(viewModel.coeffCProperty());
        textFieldCoeffC.focusedProperty().addListener(focusChangedObserver);

        buttonSolveEquation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                viewModel.solveQuadraticEquation();
            }
        });
    }
}
