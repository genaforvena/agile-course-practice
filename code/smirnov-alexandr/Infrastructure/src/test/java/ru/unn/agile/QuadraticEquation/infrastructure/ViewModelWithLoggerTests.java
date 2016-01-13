package ru.unn.agile.QuadraticEquation.infrastructure;

import ru.unn.agile.QuadraticEquationSolver.viewmodel.ViewModel;
import ru.unn.agile.QuadraticEquationSolver.viewmodel.ViewModelTests;

public class ViewModelWithLoggerTests extends ViewModelTests {
    public void setUp() {
        QuadraticEquationLogger quadraticEquationLogger =
                new QuadraticEquationLogger("./ViewModelWithLoggerTests.log");
        super.setExternalViewModel(new ViewModel(quadraticEquationLogger));
    }
}
