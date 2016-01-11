package ru.unn.agile.TreeStructure.viewmodel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class TreeViewModelTests {
    private TreeViewModel viewModel, viewDefaultModel;

    @Before
    public void initializeViewModel() {
        viewModel = new TreeViewModel();
        viewModel.setKey("100");
        viewModel.setDataFromNode("qwerty");

        viewModel.doOperation();
    }

    @Test
    public void byDefaultDoButtonIsDisabled() {
        viewDefaultModel = new TreeViewModel();

        assertFalse(viewDefaultModel.isDoButtonEnabled());
    }

    @Test
    public void byDefaultDataTextFieldIsDisabled() {
        assertTrue(viewModel.isDataTextFieldEnabled());
    }

    @Test
    public void whenKeyTextFieldClearDoButtonIsDisabled() {
        viewModel.setKey("");

        assertFalse(viewModel.isDoButtonEnabled());
    }

    @Test
    public void canSelectTypeOperation() {
        viewModel.setOperation("Truncate");

        assertEquals(TreeViewModel.Operation.TRUNCATE, viewModel.getOperation());
    }

    @Test
    public void canInsertNewNode() {
        assertEquals(TreeViewModel.ErrorMessage.SUCCESS, viewModel.getErrorMessage());
    }

    @Test
    public void displayErrorMessageWhenTryingToAddExistingElement() {
        viewModel.doOperation();

        assertEquals(TreeViewModel.ErrorMessage.ALREADY_EXISTS, viewModel.getErrorMessage());
    }

    @Test
    public void canSearchExistElement() {
        viewModel.setOperation("Search");

        viewModel.doOperation();

        assertEquals("qwerty", viewModel.getSearchedData());
    }

    @Test
    public void displayErrorMessageWhenTryingToSearchNotExistElement() {
        viewModel.setKey("10");
        viewModel.setOperation("Search");

        viewModel.doOperation();

        assertEquals(TreeViewModel.ErrorMessage.NOT_FOUND, viewModel.getErrorMessage());
    }

    @Test
    public void displayErrorMessageWhenTryingToTruncateAnExistKey() {
        viewModel.setOperation("Truncate");
        viewModel.doOperation();

        viewModel.setOperation("Search");
        viewModel.doOperation();

        assertEquals(TreeViewModel.ErrorMessage.NOT_FOUND, viewModel.getErrorMessage());
    }

    @Test
    public void displayErrorMessageWhenTryingToTruncateAnNotExistKey() {
        viewModel.setKey("101");
        viewModel.setOperation("Truncate");

        viewModel.doOperation();

        assertEquals(TreeViewModel.ErrorMessage.NOT_FOUND, viewModel.getErrorMessage());
    }

    @Test
    public void displayErrorMessageWhenKeyNotComparable() {
        viewModel.setKey("qwe");

        viewModel.doOperation();

        assertEquals(TreeViewModel.ErrorMessage.KEY_NOT_CORRECT, viewModel.getErrorMessage());
    }

    @Test
    public void canReturnNameCurrentOperation() {
        assertEquals(TreeViewModel.Operation.INSERT.toString(),
                viewModel.getOperation().toString());
    }
}
