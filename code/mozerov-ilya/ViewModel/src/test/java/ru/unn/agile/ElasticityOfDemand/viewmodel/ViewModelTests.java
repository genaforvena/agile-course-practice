package ru.unn.agile.ElasticityOfDemand.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.oldPriceProperty().get());
        assertEquals("", viewModel.oldDemandQuantityProperty().get());
        assertEquals("", viewModel.newPriceProperty().get());
        assertEquals("", viewModel.newDemandQuantityProperty().get());
        assertEquals("", viewModel.resultProperty().get());
        assertEquals(ViewModel.Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenCalculateWithEmptyFields() {
        viewModel.calculate();
        assertEquals(ViewModel.Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenFieldsAreFill() {
        setInputData();

        assertEquals(ViewModel.Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        setInputData();
        viewModel.oldPriceProperty().set("-1223");

        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingIfNotEnoughCorrectData() {
        viewModel.oldPriceProperty().set("1");

        assertEquals(ViewModel.Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledInitially() {
        assertTrue(viewModel.isCalculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWhenFormatIsBad() {
        setInputData();
        viewModel.oldPriceProperty().set("trash");

        assertTrue(viewModel.isCalculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsDisabledWithIncompleteInput() {
        viewModel.oldPriceProperty().set("1");

        assertTrue(viewModel.isCalculationDisabledProperty().get());
    }

    @Test
    public void calculateButtonIsEnabledWithCorrectInput() {
        setInputData();

        assertFalse(viewModel.isCalculationDisabledProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        setInputData();

        viewModel.calculate();

        assertEquals(ViewModel.Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        setInputData();
        viewModel.oldPriceProperty().set("yabi");

        assertEquals(ViewModel.Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        setInputData();

        assertEquals(ViewModel.Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void calculationsHaveCorrectResult() {
        viewModel.newPriceProperty().set("10");
        viewModel.oldPriceProperty().set("9");
        viewModel.newDemandQuantityProperty().set("110");
        viewModel.oldDemandQuantityProperty().set("150");

        viewModel.calculate();

        assertTrue(viewModel.resultProperty().get().startsWith("-2.39"));
    }


    private void setInputData() {
        viewModel.oldPriceProperty().set("1");
        viewModel.oldDemandQuantityProperty().set("2");
        viewModel.newPriceProperty().set("3");
        viewModel.newDemandQuantityProperty().set("4");
    }
}
