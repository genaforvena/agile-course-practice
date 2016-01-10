package ru.unn.agile.QuadraticEquationSolver.viewmodel;

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
        assertEquals(Status.WAIT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void byDefaultButtonSolveEquationIsDisabled() {
        assertTrue(viewModel.solvingEquationDisabledProperty().get());
    }

    @Test
    public void whenEnteredProperlyAllCoefficientsStatusIsReady() {
        setCoefficients("1", "2", "3");
        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenEnteredNotNumberStatusIsBadData() {
        viewModel.coeffAProperty().set("blabla");
        assertEquals(Status.BAD_DATA.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenNotAllCoefficientsEnteredStatusIsWait() {
        viewModel.coeffAProperty().set("4");
        viewModel.coeffCProperty().set("2");
        assertEquals(Status.WAIT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenEnteredCorrectDataButtonSolveEquationIsEnabled() {
        setCoefficients("1", "2", "3");
        assertFalse(viewModel.solvingEquationDisabledProperty().get());
    }

    @Test
    public void whenEnteredBadDataButtonSolveEquationIsDisabled() {
        setCoefficients("1", "2", "dich");
        assertTrue(viewModel.solvingEquationDisabledProperty().get());
    }

    @Test
    public void whenQuadraticEquationHasTwoRootsStatusIsSolved() {
        setCoefficients("1", "-5", "6");
        viewModel.solveQuadraticEquation();
        assertEquals(Status.SOLVED.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenQuadraticEquationHasOneRootStatusIsSolved() {
        setCoefficients("1", "-2", "1");
        viewModel.solveQuadraticEquation();
        assertEquals(Status.SOLVED.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void whenDiscriminantOfQuadraticEquationLessThenZeroStatusIsNoRoots() {
        setCoefficients("1", "2", "3");
        viewModel.solveQuadraticEquation();
        assertEquals(Status.NO_ROOTS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canGetCorrectSolutionOfQuadraticEquationWithTwoRoots() {
        setCoefficients("1", "-7", "6");
        viewModel.solveQuadraticEquation();
        assertEquals("1.0; 6.0", viewModel.resultProperty().get());
    }

    @Test
    public void canGetCorrectSolutionOfQuadraticEquationWithOneRoot() {
        setCoefficients("1", "-4", "4");
        viewModel.solveQuadraticEquation();
        assertEquals("2.0", viewModel.resultProperty().get());
    }

    @Test
    public void whenDiscriminantOfQuadraticEquationLessThenZeroResultIsEmpty() {
        setCoefficients("1", "3", "5");
        viewModel.solveQuadraticEquation();
        assertEquals("", viewModel.resultProperty().get());
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
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }
        assertEquals(message, "null pointer");
    }

    @Test
    public void whenCoefficientsNotEnteredLogIsEmpty() {
        setCoefficients("", "", "");
        assertTrue(viewModel.getLog().isEmpty());
    }

    @Test
    public void whenEquationWasSolvedLogContainsCorrectMessage() {
        setCoefficients("2", "4", "-6");
        viewModel.solveQuadraticEquation();
        String message = viewModel.getLog().get(0);
        assertTrue(message.contains(LogMessages.SOLVE_BUTTON_WAS_PRESSED.toString()));
    }

    @Test
    public void whenEquationWasSolvedLogContainsInputCoefficients() {
        setCoefficients("2", "-7", "5");
        viewModel.solveQuadraticEquation();
        String message = viewModel.getLog().get(0);
        assertTrue(message.contains("2") && message.contains("-7") && message.contains("5"));
    }

    @Test
    public void coefficientsInLogHaveCorrectFormat() {
        setCoefficients("3", "-8", "5");
        viewModel.solveQuadraticEquation();
        String message = getLogFullMessage();
        assertTrue(message.contains("Coefficients: a = 3.0; b = -8.0; c = 5.0"));
    }

    @Test
    public void whenFocusChangedCoefficientsProperlyLogged() {
        setCoefficients("1", "-6", "5");
        viewModel.onFocusChanged(true, false);
        String message = getLogFullMessage();
        assertTrue(message.contains("Input data was updated.  Input coefficients are: 1;-6;5"));
    }

    private String getLogFullMessage() {
        List<String> logLines = viewModel.getLog();
        String message = "";
        for (String logLine : logLines) {
            message += logLine;
        }
        return message;
    }

    private void setCoefficients(final String a, final String b, final String c) {
        viewModel.coeffAProperty().set(a);
        viewModel.coeffBProperty().set(b);
        viewModel.coeffCProperty().set(c);
    }
}
