package ru.unn.agile.Stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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
        assertEquals(Arrays.asList(), viewModel.getStackAsList());
    }

    @Test
    public void byDefault_ConvertButtonIsDisabled() {
        assertFalse(viewModel.isPushButtonEnabled());
    }

    @Test
    public void whenEnterPushValue_ConvertPushButtonIsEnabled() {
        viewModel.setTextFieldPush("1");

        assertTrue(viewModel.isPushButtonEnabled());
    }

    @Test
    public void whenClearArabicNumber_ConvertPushButtonIsDisabled() {
        viewModel.setTextFieldPush("1");
        viewModel.setTextFieldPush("");

        assertFalse(viewModel.isPushButtonEnabled());
    }

    @Test
    public void whenPush1_DisplayStackWith1() {
        viewModel.setTextFieldPush("1");
        viewModel.pressPushButton();

        assertEquals(Arrays.asList("1"), viewModel.getStackAsList());
    }

    @Test
    public void whenPopFromStack1_DisplayStackWithEmpty() {
        viewModel.setTextFieldPush("1");
        viewModel.pressPushButton();
        viewModel.pressPopButton();

        assertEquals(Arrays.asList(), viewModel.getStackAsList());
    }

    @Test
    public void byDefault_ConvertPopButtonIsDisabled() {
        assertFalse(viewModel.isPopButtonEnabled());
    }

    @Test
    public void whenStackIsNotEmpty_ConvertPopButtonEnabled() {
        viewModel.setTextFieldPush("10");
        viewModel.pressPushButton();

        assertTrue(viewModel.isPopButtonEnabled());
    }

    @Test
    public void whenStackIsEmpty_ConvertPopButtonDisabled() {
        viewModel.setTextFieldPush("10");
        viewModel.pressPushButton();
        viewModel.pressPopButton();

        assertFalse(viewModel.isPopButtonEnabled());
    }

    @Test
    public void add1234567valuesToStack() {
        viewModel.setTextFieldPush("1");
        viewModel.pressPushButton();
        viewModel.setTextFieldPush("2");
        viewModel.pressPushButton();
        viewModel.setTextFieldPush("3");
        viewModel.pressPushButton();
        viewModel.setTextFieldPush("4");
        viewModel.pressPushButton();
        viewModel.setTextFieldPush("5");
        viewModel.pressPushButton();

        assertEquals(Arrays.asList("1", "2", "3", "4", "5"), viewModel.getStackAsList());
    }
}
