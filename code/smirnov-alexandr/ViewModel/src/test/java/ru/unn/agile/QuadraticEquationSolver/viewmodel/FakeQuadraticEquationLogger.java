package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeQuadraticEquationLogger implements IQuadraticEquationLogger {
    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void log(final String message) {
        log.add(message);
    }

    @Override
    public List<String> getLog() {
        return log;
    }
}
