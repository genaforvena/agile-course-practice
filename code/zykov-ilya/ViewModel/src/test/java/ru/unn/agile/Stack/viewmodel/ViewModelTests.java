package ru.unn.agile.Stack.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void byDefaultPushButtonIsDisabled() {
        assertFalse(viewModel.isPushButtonEnabled());
    }

    @Test
    public void byDefaultPopButtonIsDisabled() {
        assertFalse(viewModel.isPopButtonEnabled());
    }

    @Test
    public void byDefaultPushTextFieldIsEmpty() {
        assertEquals("", viewModel.getTextFieldPush());
    }

    @Test
    public void byDefaultStackIsEmpty() {
        assertEquals(Arrays.asList(), viewModel.getStackAsList());
    }
    
    @Test
    public void whenEnterPushValueConvertPushButtonIsEnabled() {
        viewModel.setTextFieldPush("1");

        assertTrue(viewModel.isPushButtonEnabled());
    }

    @Test
    public void whenClearPushTextFieldPushButtonIsDisabled() {
        viewModel.setTextFieldPush("1");
        viewModel.setTextFieldPush("");

        assertFalse(viewModel.isPushButtonEnabled());
    }

    @Test
    public void whenPush1DisplayStackWith1() {
        viewModel.setTextFieldPush("1");
        viewModel.pressPushButton();

        assertEquals(Arrays.asList("1"), viewModel.getStackAsList());
    }

    @Test
    public void whenPopFromStack1DisplayStackWithEmpty() {
        viewModel.setTextFieldPush("1");
        viewModel.pressPushButton();
        viewModel.pressPopButton();

        assertEquals(Arrays.asList(), viewModel.getStackAsList());
    }

    @Test
    public void whenStackIsNotEmptyConvertPopButtonEnabled() {
        viewModel.setTextFieldPush("10");
        viewModel.pressPushButton();

        assertTrue(viewModel.isPopButtonEnabled());
    }

    @Test
    public void whenStackIsEmptyConvertPopButtonDisabled() {
        viewModel.setTextFieldPush("10");
        viewModel.pressPushButton();
        viewModel.pressPopButton();

        assertFalse(viewModel.isPopButtonEnabled());
    }

    @Test
    public void addSomeValuesToStack() {
        for (Integer i = 1; i < 6; i++) {
            viewModel.setTextFieldPush(i.toString());
            viewModel.pressPushButton();
        }

        LinkedList<String> exeptedValues = new LinkedList<String>();
        for (Integer i = 1; i < 6; i++) {
            exeptedValues.add(i.toString());
        }

        assertEquals(exeptedValues, viewModel.getStackAsList());
    }
}
