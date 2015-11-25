package ru.unn.agile.Stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() { viewModel = new ViewModel(); }

    @After
    public void tearDown() { viewModel = null; }

    @Test
    public void canSetDefaultValues() {
        assertEquals("", viewModel.getTextFieldPush());
        assertEquals("", viewModel.getResult());
    }
}
