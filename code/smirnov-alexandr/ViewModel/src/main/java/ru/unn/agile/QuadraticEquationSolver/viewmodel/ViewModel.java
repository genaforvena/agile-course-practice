package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.QuadraticEquationSolver.Model.QuadraticEquationSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewModel {
    private final StringProperty a = new SimpleStringProperty();
    private final StringProperty b = new SimpleStringProperty();
    private final StringProperty c = new SimpleStringProperty();
    private final BooleanProperty solvingEquationDisabled = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private IQuadraticEquationLogger qeLogger;
    private List<ValueChangeObserver> valueChangedObservers;
    private final StringProperty logs = new SimpleStringProperty();

    public StringProperty coeffAProperty() {
        return a;
    }

    public StringProperty coeffBProperty() {
        return b;
    }

    public StringProperty coeffCProperty() {
        return c;
    }

    public BooleanProperty solvingEquationDisabledProperty() {
        return solvingEquationDisabled;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public final String getStatus() {
        return status.get();
    }

    public final boolean getSolvingEquationDisabled() {
        return solvingEquationDisabled.get();
    }

    public final List<String> getLog() {
        return qeLogger.getLog();
    }

    public StringProperty logsProperty() {
        return logs;
    }

    public final String getLogs() {
        return logs.get();
    }

    public ViewModel() {
        initAllFields();
    }

    public ViewModel(final IQuadraticEquationLogger logger) {
        createLogger(logger);
        initAllFields();
    }

    public void createLogger(final IQuadraticEquationLogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("null pointer");
        }
        qeLogger = logger;
    }

    private void initAllFields() {
        a.set("");
        b.set("");
        c.set("");
        result.set("");
        status.set(Status.WAIT.toString());

        BooleanBinding canSolveEquation = new BooleanBinding() {
            {
                super.bind(a, b, c);
            }
            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        solvingEquationDisabled.bind(canSolveEquation.not());

        final List<StringProperty> textFieldsCoefficients = new ArrayList<StringProperty>() { {
            add(a);
            add(b);
            add(c);
        } };

        valueChangedObservers = new ArrayList<>();
        for (StringProperty textFieldCoefficient : textFieldsCoefficients) {
            final ValueChangeObserver observer = new ValueChangeObserver();
            textFieldCoefficient.addListener(observer);
            valueChangedObservers.add(observer);
        }
    }

    public void solveQuadraticEquation() {
        float coeffA = Float.parseFloat(a.get());
        float coeffB = Float.parseFloat(b.get());
        float coeffC = Float.parseFloat(c.get());
        float [] roots = trySolveQuadraticEquation(coeffA, coeffB, coeffC);
        if (roots == null) {
            status.set(Status.NO_ROOTS.toString());
        } else {
            status.set(Status.SOLVED.toString());
        }
        result.set(createAnswerInStringFormat(roots));

        String message = String.format("%s Coefficients: a = %s; b = %s; c = %s",
                LogMessages.SOLVE_BUTTON_WAS_PRESSED, coeffA, coeffB, coeffC);
        qeLogger.log(message);
        changeLogs();
    }

    private String createAnswerInStringFormat(final float [] roots) {
        String outAnswer = "";
        if (roots != null) {
            ArrayList<String> answer = new ArrayList<>();
            for (float root : roots) {
                answer.add(String.valueOf(root));
            }
            outAnswer = String.join("; ", answer);
        }
        return outAnswer;
    }

    private float [] trySolveQuadraticEquation(final float a, final float b, final float c) {
        try {
            return QuadraticEquationSolver.solve(a, b, c);
        } catch (Exception ex) {
            return null;
        }
    }

    public void onFocusChanged(final Boolean oldState, final Boolean newState) {
        if (newState && !oldState) {
            return;
        }
        for (ValueChangeObserver observer : valueChangedObservers) {
            if (observer.wasChanged()) {
                String message = String.format("%s Input coefficients are: %s;%s;%s",
                        LogMessages.EDITING_WAS_FINISHED, a.get(), b.get(), c.get());
                qeLogger.log(message);
                changeLogs();
                observer.cache();
                break;
            }
        }
    }

    private Status getInputStatus() {
        Status status = Status.READY;
        if (a.get().isEmpty() || b.get().isEmpty() || c.get().isEmpty()) {
            status = Status.WAIT;
        }
        try {
            if (!a.get().isEmpty()) {
                Float.parseFloat(a.get());
            }
            if (!b.get().isEmpty()) {
                Float.parseFloat(b.get());
            }
            if (!c.get().isEmpty()) {
                Float.parseFloat(c.get());
            }
        } catch (NumberFormatException nfe) {
            status = Status.BAD_DATA;
        }

        return status;
    }

    private class ValueChangeObserver implements ChangeListener<String> {
        private String previousValue = "";
        private String currentValue = "";
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldState, final String newState) {
            if (Objects.equals(newState, oldState)) {
                return;
            }
            status.set(getInputStatus().toString());
            currentValue = newState;
        }
        public boolean wasChanged() {
            return !(Objects.equals(currentValue, previousValue));
        }
        public void cache() {
            previousValue = currentValue;
        }
    }

    private void changeLogs() {
        String record = "";
        List<String> log = qeLogger.getLog();
        for (String logLine : log) {
            record += String.format("%s\n", logLine);
        }
        logs.set(record);
    }
}

enum Status {
    WAIT("Waiting enter coefficients"),
    READY("Ready to solve equation"),
    BAD_DATA("You entered wrong number"),
    NO_ROOTS("Equation has no roots"),
    SOLVED("Equation was solved");

    private final String name;
    private Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}

enum LogMessages {
    SOLVE_BUTTON_WAS_PRESSED("Solving. "),
    EDITING_WAS_FINISHED("Input data was updated. ");

    private final String message;
    private LogMessages(final String message) {
        this.message = message;
    }
    public String toString() {
        return message;
    }
}
