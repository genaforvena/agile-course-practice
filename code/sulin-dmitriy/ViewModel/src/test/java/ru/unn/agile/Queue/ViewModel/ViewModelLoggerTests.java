package ru.unn.agile.Queue.ViewModel;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelLoggerTests {

    private LabQueueViewModel viewModel;

    @Before
    public void initialize() {

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
}
