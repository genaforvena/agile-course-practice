package ru.unn.agile.Triangle;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.Triangle.Model.TriangleExceptions;
import ru.unn.agile.TriangleViewModel.Status;
import ru.unn.agile.TriangleViewModel.TriangleViewModel;
import ru.unn.agile.TriangleViewModel.ValuesToCalculate;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

public class TriangleViewModelTests {
    private TriangleViewModel viewModel;
    @Before
    public void setUp() {
        FakeLogger fakeLogger = new FakeLogger();
        viewModel = new TriangleViewModel(fakeLogger);
    }

    private void setExampleValues() {
        viewModel.setCoordinate1X("9.65");
        viewModel.setCoordinate1Y("5");
        viewModel.setCoordinate1Z("-2.0");
        viewModel.setCoordinate2X("3");
        viewModel.setCoordinate2Y("10");
        viewModel.setCoordinate2Z("-1");
        viewModel.setCoordinate3X("-0.5");
        viewModel.setCoordinate3Y("1.5");
        viewModel.setCoordinate3Z("1.5");
        viewModel.setValueToCalculate(ValuesToCalculate.PERIMETER);
    }
    @Test
    public void byDefaultCoordinate1XisEmptyString() {
        assertEquals(viewModel.getCoordinate1X(), "");
    }

    @Test
    public void byDefaultCoordinate1YisEmptyString() {
        assertEquals(viewModel.getCoordinate1Y(), "");
    }

    @Test
    public void byDefaultCoordinate1ZisEmptyString() {
        assertEquals(viewModel.getCoordinate1Z(), "");
    }

    @Test
    public void byDefaultCoordinate2XisEmptyString() {
        assertEquals(viewModel.getCoordinate2X(), "");
    }

    @Test
    public void byDefaultCoordinate2YisEmptyString() {
        assertEquals(viewModel.getCoordinate2Y(), "");
    }

    @Test
    public void byDefaultCoordinate2ZisEmptyString() {
        assertEquals(viewModel.getCoordinate2Z(), "");
    }

    @Test
    public void byDefaultCoordinate3XisEmptyString() {
        assertEquals(viewModel.getCoordinate3X(), "");
    }

    @Test
    public void byDefaultCoordinate3YisEmptyString() {
        assertEquals(viewModel.getCoordinate3Y(), "");
    }

    @Test
    public void byDefaultCoordinate3ZisEmptyString() {
        assertEquals(viewModel.getCoordinate3Z(), "");
    }

    @Test
    public void byDefaultResultIsZero() {
        assertEquals(viewModel.getResult(), "0.0");
    }

    @Test
    public void byDefaultValueToCalculateIsMEDIANS() {
        assertEquals(viewModel.getValueToCalculate(), ValuesToCalculate.MEDIANS);
    }

    @Test
    public void byDefaultStatusIsWaiting() {
        assertEquals(Status.WAITING, viewModel.getStatus());
    }

    @Test
    public void byDefaultCalculateButtonIsDisabled() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void enterExampleValuesIsFinishedWithRightStatus() throws Exception {
        setExampleValues();
        viewModel.checkInputAndChangeStateIfOK();
        assertEquals(Status.READY, viewModel.getStatus());
    }

    @Test
    public void computeExampleIsFinishedWithRightStatus() throws Exception {
        setExampleValues();
        viewModel.compute();
        assertEquals(Status.SUCCESS, viewModel.getStatus());
    }

    @Test
    public void whenAllFieldsAreCompletedCorrectlyButtonIsEnabled() {
        setExampleValues();
        viewModel.checkInputAndChangeStateIfOK();
        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenEnterStringStatusIsBadFormat() {
        viewModel.setCoordinate1X("Ooo");
        viewModel.checkInputAndChangeStateIfOK();
        assertEquals(Status.BAD_FORMAT, viewModel.getStatus());
    }

    @Test
    public void whenTriangleIsDegenerateStatusIsCorrect() throws Exception {
        viewModel.setCoordinate1X("1");
        viewModel.setCoordinate1Y("1");
        viewModel.setCoordinate1Z("1");
        viewModel.setCoordinate2X("2");
        viewModel.setCoordinate2Y("2");
        viewModel.setCoordinate2Z("2");
        viewModel.setCoordinate3X("3");
        viewModel.setCoordinate3Y("3");
        viewModel.setCoordinate3Z("3");
        viewModel.setValueToCalculate(ValuesToCalculate.PERIMETER);
        viewModel.compute();
        assertEquals(viewModel.getStatus(), TriangleExceptions.DEGENERATE_TRIANGLE.toString());
    }

    @Test
    public void buttonIsDisabledWhenNotFieldsAreFilled() {
        viewModel.setCoordinate1X("1");
        assertEquals(viewModel.isCalculateButtonEnabled(), false);
    }

    @Test
    public void canViewModelWorkWithLogger() {
        FakeLogger fakeLogger = new FakeLogger();
        TriangleViewModel viewModelWithFakeLogger = new TriangleViewModel(fakeLogger);
        assertNotNull(viewModelWithFakeLogger);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canViewModelThrowExceptionWhenLoggerIsNull() {
        new TriangleViewModel(null);
    }

    @Test
    public void isLoggerEmptyByDefault() {
        List<String> log = viewModel.getLog();
        assertEquals(true, log.isEmpty());
    }

    @Test
    public void doLoggerIncludeCoordinate1X() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(0);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate1X().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate1Y() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(1);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate1Y().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate1Z() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(2);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate1Z().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate2X() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(3);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate2X().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate2Y() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(4);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate2Y().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate2Z() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(5);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate2Z().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate3X() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(6);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate3X().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate3Y() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(7);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate3Y().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeCoordinate3Z() throws Exception {
        setExampleValues();
        viewModel.compute();
        String log = viewModel.getLog().get(8);
        boolean doesContainTemplate = log.contains(viewModel.getCoordinate3Z().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void doLoggerIncludeValueToCalculate() throws Exception {
        setExampleValues();
        String log = viewModel.getLog().get(9);
        boolean doesContainTemplate = log.contains(viewModel.getValueToCalculate().toString());
        assertTrue(doesContainTemplate);
    }

    @Test
    public void isRecordCorrectWhenCoordinate1XChanged() {
        setExampleValues();
        viewModel.setCoordinate1X("1.25");
        String rightRecord = "New value was chosen for "
                + "coordinate X of point 1 : " + viewModel.getCoordinate1X();
        String record = viewModel.getLog().get(10);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate1XIsTheSame() {
        viewModel.setCoordinate1X("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCoordinate1YChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Y of point 1 : " + viewModel.getCoordinate1Y();
        String record = viewModel.getLog().get(1);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate1YIsTheSame() {
        viewModel.setCoordinate1Y("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCoordinate1ZChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Z of point 1 : " + viewModel.getCoordinate1Z();
        String record = viewModel.getLog().get(2);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate1ZIsTheSame() {
        viewModel.setCoordinate1Z("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCoordinate2XChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate X of point 2 : " + viewModel.getCoordinate2X();
        String record = viewModel.getLog().get(3);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate2XIsTheSame() {
        viewModel.setCoordinate2X("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCoordinate2YChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Y of point 2 : " + viewModel.getCoordinate2Y();
        String record = viewModel.getLog().get(4);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate2YIsTheSame() {
        viewModel.setCoordinate2Y("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCoordinate2ZChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Z of point 2 : " + viewModel.getCoordinate2Z();
        String record = viewModel.getLog().get(5);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate2ZIsTheSame() {
        viewModel.setCoordinate2Z("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
         public void isRecordCorrectWhenCoordinate3XChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate X of point 3 : " + viewModel.getCoordinate3X();
        String record = viewModel.getLog().get(6);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate3XIsTheSame() {
        viewModel.setCoordinate3X("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCoordinate3YChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Y of point 3 : " + viewModel.getCoordinate3Y();
        String record = viewModel.getLog().get(7);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate3YIsTheSame() {
        viewModel.setCoordinate3Y("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCoordinate3ZChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "coordinate Z of point 3 : " + viewModel.getCoordinate3Z();
        String record = viewModel.getLog().get(8);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenCoordinate3ZIsTheSame() {
        viewModel.setCoordinate3Z("");
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenValueToCalculateChanged() {
        setExampleValues();
        String rightRecord = "New value was chosen for "
                + "value to calculate : " + viewModel.getValueToCalculate();
        String record = viewModel.getLog().get(9);
        assertThat(record, containsString(rightRecord));
    }

    @Test
    public void noRecordWhenValueToCalculateIsTheSame() {
        viewModel.setValueToCalculate(ValuesToCalculate.MEDIANS);
        List<String> log = viewModel.getLog();
        assertEquals(0, log.size());
    }

    @Test
    public void isRecordCorrectWhenCalculate() throws Exception {
        setExampleValues();
        viewModel.compute();
        List<String> log = viewModel.getLog();
        assertTrue(log.size() > 10);
    }

    public void setViewModel(TriangleViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
