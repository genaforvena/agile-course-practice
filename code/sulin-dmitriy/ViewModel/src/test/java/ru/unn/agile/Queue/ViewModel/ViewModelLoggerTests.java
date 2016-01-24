package ru.unn.agile.Queue.ViewModel;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ViewModelLoggerTests {
    private LabQueueViewModel viewModel;

    @Before
    public void setUpViewModel() {
        viewModel = new LabQueueViewModel(new FakeQueueLogger());
    }

    @Test
    public void canCreateViewModelWithFakeLogger() {
        assertNotNull(viewModel);
    }

    @Test
    public void byDefaultLogIsEmpty() {
        assertTrue(viewModel.getAllRecords().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void canViewModelThrowExceptionWhenLoggerIsNull() {
        new LabQueueViewModel(null);
    }

    @Test
    public void doLoggerContainOperationAfterPush() {
        viewModel.setElement("something");
        viewModel.pushElement();

        String record = viewModel.getAllRecords().get(0);

        assertThat(record, containsString(NamesOfOperations.OPERATION_PUSH));
    }

    @Test
    public void doLoggerContainOperationAfterPop() {
        viewModel.setElement("something");
        viewModel.pushElement();
        viewModel.popElement();

        String record = viewModel.getAllRecords().get(1);

        assertThat(record, containsString(NamesOfOperations.OPERATION_POP));
    }

    @Test
    public void doLoggerContainOperationAfterFind() {
        viewModel.setElement("something");
        viewModel.findElement();

        String record = viewModel.getAllRecords().get(0);

        assertThat(record, containsString(NamesOfOperations.OPERATION_FIND));
    }
}
