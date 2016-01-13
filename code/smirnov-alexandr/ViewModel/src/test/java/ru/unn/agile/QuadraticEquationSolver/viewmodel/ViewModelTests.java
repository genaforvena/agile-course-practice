package ru.unn.agile.QuadraticEquationSolver.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    public void setExternalViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void setUp() {
        if (viewModel == null) {
            viewModel = new ViewModel(new FakeQuadraticEquationLogger());
        }
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void byDefaultStatusIsWait() {
        Status expectedStatus = Status.WAIT;
        StringProperty actualStatus = viewModel.statusProperty();
        assertEquals(expectedStatus.toString(), actualStatus.get());
    }

    @Test
    public void byDefaultButtonSolveEquationIsDisabled() {
        BooleanProperty solvingEquationDisabled = viewModel.solvingEquationDisabledProperty();
        assertTrue(solvingEquationDisabled.get());
    }

    @Test
    public void whenEnteredProperlyAllCoefficientsStatusIsReady() {
        Status expectedStatus = Status.READY;
        StringProperty actualStatus = viewModel.statusProperty();
        setCoefficients("1", "2", "3");
        assertEquals(expectedStatus.toString(), actualStatus.get());
    }

    @Test
    public void whenEnteredNotNumberStatusIsBadData() {
        StringProperty coefficientA = viewModel.coefficientAProperty();
        Status expectedStatus = Status.BAD_DATA;
        StringProperty actualStatus = viewModel.statusProperty();
        coefficientA.set("blabla");
        assertEquals(expectedStatus.toString(), actualStatus.get());
    }

    @Test
    public void whenNotAllCoefficientsEnteredStatusIsWait() {
        StringProperty coefficientA = viewModel.coefficientAProperty();
        StringProperty coefficientC = viewModel.coefficientCProperty();
        Status expectedStatus = Status.WAIT;
        StringProperty actualStatus = viewModel.statusProperty();
        coefficientA.set("4");
        coefficientC.set("2");
        assertEquals(expectedStatus.toString(), actualStatus.get());
    }

    @Test
    public void whenEnteredCorrectDataButtonSolveEquationIsEnabled() {
        BooleanProperty solvingEquationDisabled = viewModel.solvingEquationDisabledProperty();
        setCoefficients("1", "2", "3");
        assertFalse(solvingEquationDisabled.get());
    }

    @Test
    public void whenEnteredBadDataButtonSolveEquationIsDisabled() {
        BooleanProperty solvingEquationDisabled = viewModel.solvingEquationDisabledProperty();
        setCoefficients("1", "2", "dich");
        assertTrue(solvingEquationDisabled.get());
    }

    @Test
    public void whenQuadraticEquationHasTwoRootsStatusIsSolved() {
        setCoefficients("1", "-5", "6");
        viewModel.solveQuadraticEquation();
        Status expectedStatus = Status.SOLVED;
        StringProperty actualStatus = viewModel.statusProperty();
        assertEquals(expectedStatus.toString(), actualStatus.get());
    }

    @Test
    public void whenQuadraticEquationHasOneRootStatusIsSolved() {
        setCoefficients("1", "-2", "1");
        viewModel.solveQuadraticEquation();
        Status expectedStatus = Status.SOLVED;
        StringProperty actualStatus = viewModel.statusProperty();
        assertEquals(expectedStatus.toString(), actualStatus.get());
    }

    @Test
    public void whenDiscriminantOfQuadraticEquationLessThenZeroStatusIsNoRoots() {
        setCoefficients("1", "2", "3");
        viewModel.solveQuadraticEquation();
        Status expectedStatus = Status.NO_ROOTS;
        StringProperty actualStatus = viewModel.statusProperty();
        assertEquals(expectedStatus.toString(), actualStatus.get());
    }

    @Test
    public void canGetCorrectSolutionOfQuadraticEquationWithTwoRoots() {
        setCoefficients("1", "-7", "6");
        viewModel.solveQuadraticEquation();
        StringProperty actualResult = viewModel.resultProperty();
        assertEquals("1.0; 6.0", actualResult.get());
    }

    @Test
    public void canGetCorrectSolutionOfQuadraticEquationWithOneRoot() {
        setCoefficients("1", "-4", "4");
        viewModel.solveQuadraticEquation();
        StringProperty actualResult = viewModel.resultProperty();
        assertEquals("2.0", actualResult.get());
    }

    @Test
    public void whenDiscriminantOfQuadraticEquationLessThenZeroResultIsEmpty() {
        setCoefficients("1", "3", "5");
        viewModel.solveQuadraticEquation();
        StringProperty actualResult = viewModel.resultProperty();
        assertEquals("", actualResult.get());
    }

    @Test
    public void logIsEmptyByDefault() {
        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void whenViewModelCreatedWithNullLoggerGetException() {
        String message = "";
        try {
            new ViewModel(null);
        } catch (IllegalArgumentException illegalArgumentException) {
            message = illegalArgumentException.getMessage();
        }
        assertEquals(message, "null pointer");
    }

    @Test
    public void whenCoefficientsNotEnteredLogIsEmpty() {
        setCoefficients("", "", "");
        List<String> log = viewModel.getLog();
        assertTrue(log.isEmpty());
    }

    @Test
    public void whenEquationWasSolvedLogContainsCorrectMessage() {
        setCoefficients("2", "4", "-6");
        viewModel.solveQuadraticEquation();
        List<String> log = viewModel.getLog();
        String message = log.get(0);
        LogMessages actualMessage = LogMessages.SOLVE_BUTTON_WAS_PRESSED;
        assertTrue(message.contains(actualMessage.toString()));
    }

    @Test
    public void whenEquationWasSolvedLogContainsInputCoefficients() {
        setCoefficients("2", "-7", "5");
        viewModel.solveQuadraticEquation();
        List<String> log = viewModel.getLog();
        String message = log.get(0);
        assertTrue(message.contains("2") && message.contains("-7") && message.contains("5"));
    }

    @Test
    public void coefficientsInLogHaveCorrectFormat() {
        setCoefficients("3", "-8", "5");
        viewModel.solveQuadraticEquation();
        String message = getFullLogMessage();
        assertTrue(message.contains("Coefficients: a = 3.0; b = -8.0; c = 5.0"));
    }

    @Test
    public void whenFocusChangedCoefficientsProperlyLogged() {
        setCoefficients("1", "-6", "5");
        viewModel.onFocusChanged(true, false);
        String message = getFullLogMessage();
        assertTrue(message.contains("Input data was updated.  Input coefficients are: 1;-6;5"));
    }

    private String getFullLogMessage() {
        List<String> logLines = viewModel.getLog();
        String message = "";
        for (String logLine : logLines) {
            message += logLine;
        }
        return message;
    }

    private void setCoefficients(final String a, final String b, final String c) {
        StringProperty coefficientA = viewModel.coefficientAProperty();
        StringProperty coefficientB = viewModel.coefficientBProperty();
        StringProperty coefficientC = viewModel.coefficientCProperty();
        coefficientA.set(a);
        coefficientB.set(b);
        coefficientC.set(c);
    }
}
