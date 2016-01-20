package ru.unn.agile.MarksAccounting.viewmodel;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MarksAccounting.model.*;
import javax.swing.*;
import java.text.ParseException;

public class AddStudentDialogViewModelTests {
    private DialogViewModel addStudentViewModel;
    private TableOfMarks tableOfMarks;

    @Before
    public void setUp() {
        initModel();
        initTable();
    }

    @After
    public void tearDown() {
        addStudentViewModel = null;
        tableOfMarks = null;
    }

    private void initModel() {
        TableOfMarks tempTableOfMarks = TestDataInitializer.initTableOfMarks();
        addStudentViewModel = new AddStudentDialogViewModel(tempTableOfMarks);
    }

    private void initTable() {
        tableOfMarks = TestDataInitializer.initTableOfMarks();
    }

    @Test
    public void canSetInitialValues() {
        assertTrue(addStudentViewModel.isDialogGroupBoxVisible());
        assertFalse(addStudentViewModel.isDialogStudentBoxVisible());
        assertFalse(addStudentViewModel.isDialogSubjectBoxVisible());
        assertFalse(addStudentViewModel.isDialogDateTextBoxVisible());
        assertTrue(addStudentViewModel.isDialogInputTextBoxVisible());
        assertTrue(addStudentViewModel.getDialogGroup().isEmpty());
        assertTrue(addStudentViewModel.getDialogStudent().isEmpty());
        assertTrue(addStudentViewModel.getDialogSubject().isEmpty());
        assertTrue(addStudentViewModel.getDialogDate().isEmpty());
        assertTrue(addStudentViewModel.getDialogInputTextBox().isEmpty());
        assertEquals(DialogType.ADD_STUDENT, addStudentViewModel.getDialogType());
    }

    @Test
    public void canGetGroupsComboBoxModel() {
        assertTrue(ComboBoxModelsEqualer.comboBoxModelsEqualing(
                TestDataInitializer.getAllGroupsInComboBoxModel(),
                addStudentViewModel.getDialogGroupsComboBoxModel()));
    }

    @Test
    public void canAddStudent() {
        try {
            tableOfMarks.addStudent(new Group("2"), new Student("Smirnov"));

            addStudentViewModel.setDialogGroup("2");
            addStudentViewModel.setDialogInputTextBox("Smirnov");
            addStudentViewModel.changeTableOfMarks();

            assertEquals(tableOfMarks, addStudentViewModel.getTableOfMarks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotAddStudentWhenGroupDoesNotExist() {
        try {
            addStudentViewModel.setDialogGroup("116");
            addStudentViewModel.setDialogInputTextBox("Smirnov");
            addStudentViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = StudentAlreadyExistsException.class)
    public void canNotAddStudentWhenStudentAlreadyExists() {
        try {
            addStudentViewModel.setDialogGroup("1");
            addStudentViewModel.setDialogInputTextBox("Sidorov");
            addStudentViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = StudentAlreadyExistsException.class)
    public void canNotStudentMarkWhenStudentWithoutSpacesAlreadyExists() {
        try {
            addStudentViewModel.setDialogGroup("1");
            addStudentViewModel.setDialogInputTextBox(" Petrov    ");
            addStudentViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = InputIsEmptyException.class)
    public void canNotAddStudentWhenInputIsEmptyOrSpaces() {
        try {
            addStudentViewModel.setDialogGroup("3");
            addStudentViewModel.setDialogInputTextBox("   ");
            addStudentViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }
}
