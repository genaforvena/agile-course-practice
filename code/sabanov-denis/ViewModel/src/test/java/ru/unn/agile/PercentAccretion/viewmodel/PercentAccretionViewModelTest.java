package ru.unn.agile.PercentAccretion.viewmodel;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.PercentAccretion.Model.PercentAccretionFactory;

import static org.junit.Assert.*;

public class PercentAccretionViewModelTest {
    private PercentAccretionViewModel viewModel;

    public void setViewModel(final PercentAccretionViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Before
    public void initialize() {
        FakePercentAccretionLogger logger = new FakePercentAccretionLogger();
        viewModel = new PercentAccretionViewModel(logger);
    }

    @Test
    public void byDefaultCalculateBtnIsDisabled() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenFieldsAreEmptyShowStatusMessage() {
        assertEquals(PercentAccretionViewModel.
                        PercentAccretionStatus.FIELD_IS_EMPTY.getMessage(),
                viewModel.getStatusMessage());
    }

    @Test
    public void whenFillAllFieldsCalculateBtnIsEnabled() {
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("1");
        viewModel.setInitialSum("1");

        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenFillAllFieldsClearStatusMessage() {
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("1");
        viewModel.setInitialSum("1");

        assertEquals(PercentAccretionViewModel.
                        PercentAccretionStatus.SUCCESS.getMessage(),
                viewModel.getStatusMessage());
    }

    @Test
    public void whenOneOfFieldsIsFilledIncorrectShowStatusMessage() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("a");

        assertEquals(PercentAccretionViewModel.
                        PercentAccretionStatus.INCORRECT_VALUES.getMessage(),
                viewModel.getStatusMessage());
    }

    @Test
    public void whenCorrectWrongValueOfFieldClearStatusMessage() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("a");
        viewModel.setCountOfYears("1");

        assertEquals(PercentAccretionViewModel.
                        PercentAccretionStatus.SUCCESS.getMessage(),
                viewModel.getStatusMessage());
    }

    @Test
    public void whenOneOfFieldsIsClearedShowStatusMessage() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("1");
        viewModel.setCountOfYears("");

        assertEquals(PercentAccretionViewModel.
                        PercentAccretionStatus.FIELD_IS_EMPTY.getMessage(),
                viewModel.getStatusMessage());
    }

    @Test
    public void whenCalculateSimplePercentSumShowResult() {
        String expectedValue = "3.0";

        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType(PercentAccretionFactory.
                AccretionType.SIMPLE_PERCENT_SUM.toString());
        viewModel.calculateResultSum();

        assertEquals(expectedValue, viewModel.getResultSum());
    }

    @Test
    public void whenCalculateComplexPercentSumShowResult() {
        String expectedValue = "4.0";

        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType(PercentAccretionFactory.
                AccretionType.COMPLEX_PERCENT_SUM.toString());
        viewModel.calculateResultSum();

        assertEquals(expectedValue, viewModel.getResultSum());
    }

    @Test
    public void whenSumIsCalculatedAndOneOfFieldsIsChangedClearSum() {
        String expectedValue = "";

        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType(PercentAccretionFactory.
                AccretionType.SIMPLE_PERCENT_SUM.toString());
        viewModel.calculateResultSum();
        viewModel.setInitialSum("2");

        assertEquals(expectedValue, viewModel.getResultSum());
    }

    @Test
    public void whenSumIsCalculatedAndPercentTypeIsChangedClearSum() {
        String expectedValue = "";

        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType(PercentAccretionFactory.
                AccretionType.SIMPLE_PERCENT_SUM.toString());
        viewModel.calculateResultSum();
        viewModel.setPercentType(PercentAccretionFactory.
                AccretionType.COMPLEX_PERCENT_SUM.toString());

        assertEquals(expectedValue, viewModel.getResultSum());
    }
}
