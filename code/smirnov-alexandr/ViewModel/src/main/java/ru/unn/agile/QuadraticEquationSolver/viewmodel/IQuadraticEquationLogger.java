package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import java.util.List;

public interface IQuadraticEquationLogger {
    void log(final String message);

    List<String> getLog();
}
