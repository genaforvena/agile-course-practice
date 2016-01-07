package ru.unn.agile.MarksAccounting.viewmodel;

import org.junit.After;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MarksAccounting.model.Group;
import ru.unn.agile.MarksAccounting.model.GroupDoesNotExistException;
import ru.unn.agile.MarksAccounting.model.TableOfMarks;
import javax.swing.*;

public class DeleteGroupDialogViewModelTests {
    private DialogViewModel deleteGroupViewModel;
    private TableOfMarks tableOfMarks;

    @Before
    public void setUp() {
        initModel();
        initTable();
    }

    @After
    public void tearDown() {
        deleteGroupViewModel = null;
        tableOfMarks = null;
    }

    private void initModel() {
        TableOfMarks tempTableOfMarks = TestDataInitializer.initTableOfMarks();
        deleteGroupViewModel = new DeleteGroupDialogViewModel(tempTableOfMarks);
    }

    private void initTable() {
        tableOfMarks = TestDataInitializer.initTableOfMarks();
    }

    @Test
    public void canSetInitialValues() {
        assertTrue(deleteGroupViewModel.isDialogGroupBoxVisible());
        assertFalse(deleteGroupViewModel.isDialogStudentBoxVisible());
        assertFalse(deleteGroupViewModel.isDialogSubjectBoxVisible());
        assertFalse(deleteGroupViewModel.isDialogDateTextBoxVisible());
        assertFalse(deleteGroupViewModel.isDialogInputTextBoxVisible());
        assertTrue(deleteGroupViewModel.getDialogGroup().isEmpty());
        assertTrue(deleteGroupViewModel.getDialogStudent().isEmpty());
        assertTrue(deleteGroupViewModel.getDialogSubject().isEmpty());
        assertTrue(deleteGroupViewModel.getDialogDate().isEmpty());
        assertTrue(deleteGroupViewModel.getDialogInputTextBox().isEmpty());
        assertEquals(DialogType.DELETE_GROUP, deleteGroupViewModel.getDialogType());
    }

    @Test
    public void canGetGroupsComboBoxModel() {
        String[] groupNumbers = {"1", "2", "3"};
        ComboBoxModel<String> expectedComboBoxModel = new JComboBox<String>(
                groupNumbers).getModel();

        assertTrue(ComboBoxModelsEqualer.comboBoxModelsEqualing(expectedComboBoxModel,
                deleteGroupViewModel.getDialogGroupsComboBoxModel()));
    }

    @Test
    public void canDeleteGroup() {
        try {
            tableOfMarks.deleteGroup(new Group("1"));

            deleteGroupViewModel.setDialogGroup("1");
            deleteGroupViewModel.changeTableOfMarks();

            assertEquals(tableOfMarks, deleteGroupViewModel.getTableOfMarks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotDeleteGroupWhenGroupDoesNotExist() {
        try {
            deleteGroupViewModel.setDialogGroup("116");
            deleteGroupViewModel.changeTableOfMarks();
        } catch (Exception e) {
            if (e.getClass().equals(GroupDoesNotExistException.class)) {
                throw new GroupDoesNotExistException("Required group does not exist!");
            } else {
                fail();
            }
        }
    }
}
