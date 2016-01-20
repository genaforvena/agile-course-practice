package ru.unn.agile.MarksAccounting.viewmodel;

import org.junit.After;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MarksAccounting.model.*;
import javax.swing.*;
import java.text.ParseException;

public class DeleteStudentDialogViewModelTests {
    private DialogViewModel deleteStudentViewModel;
    private TableOfMarks tableOfMarks;

    @Before
    public void setUp() {
        initModel();
        initTable();
    }

    @After
    public void tearDown() {
        deleteStudentViewModel = null;
        tableOfMarks = null;
    }

    private void initModel() {
        TableOfMarks tempTableOfMarks = TestDataInitializer.initTableOfMarks();
        deleteStudentViewModel = new DeleteStudentDialogViewModel(tempTableOfMarks);
    }

    private void initTable() {
        tableOfMarks = TestDataInitializer.initTableOfMarks();
    }

    @Test
    public void canSetInitialValues() {
        assertTrue(deleteStudentViewModel.isDialogGroupBoxVisible());
        assertTrue(deleteStudentViewModel.isDialogStudentBoxVisible());
        assertFalse(deleteStudentViewModel.isDialogSubjectBoxVisible());
        assertFalse(deleteStudentViewModel.isDialogDateTextBoxVisible());
        assertFalse(deleteStudentViewModel.isDialogInputTextBoxVisible());
        assertTrue(deleteStudentViewModel.getDialogGroup().isEmpty());
        assertTrue(deleteStudentViewModel.getDialogStudent().isEmpty());
        assertTrue(deleteStudentViewModel.getDialogSubject().isEmpty());
        assertTrue(deleteStudentViewModel.getDialogDate().isEmpty());
        assertTrue(deleteStudentViewModel.getDialogInputTextBox().isEmpty());
        assertEquals(DialogType.DELETE_STUDENT, deleteStudentViewModel.getDialogType());
    }

    @Test
    public void canGetGroupsComboBoxModel() {
        String[] groupNumbersWhereStudentsExist = {"1", "3"};
        ComboBoxModel<String> expectedComboBoxModel = new JComboBox<String>(
                groupNumbersWhereStudentsExist).getModel();

        assertTrue(ComboBoxModelsEqualer.comboBoxModelsEqualing(expectedComboBoxModel,
                deleteStudentViewModel.getDialogGroupsComboBoxModel()));
    }

    @Test
    public void canGetStudentsComboBoxModel() {
        String[] studentsInFirstGroup = {"Petrov", "Sidorov"};
        ComboBoxModel<String> expectedComboBoxModel = new JComboBox<String>(
                studentsInFirstGroup).getModel();

        deleteStudentViewModel.setDialogGroup("1");

        assertTrue(ComboBoxModelsEqualer.comboBoxModelsEqualing(expectedComboBoxModel,
                deleteStudentViewModel.getDialogStudentsComboBoxModel()));
    }

    @Test
    public void canDeleteStudent() {
        try {
            tableOfMarks.deleteStudent(new Group("1"), new Student("Sidorov"));

            deleteStudentViewModel.setDialogGroup("1");
            deleteStudentViewModel.setDialogStudent("Sidorov");
            deleteStudentViewModel.changeTableOfMarks();

            assertEquals(tableOfMarks, deleteStudentViewModel.getTableOfMarks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotDeleteStudentWhenGroupDoesNotExist() {
        try {
            deleteStudentViewModel.setDialogGroup("116");
            deleteStudentViewModel.setDialogStudent("Sidorov");
            deleteStudentViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void canNotDeleteStudentWhenStudentDoesNotExist() {
        try {
            deleteStudentViewModel.setDialogGroup("3");
            deleteStudentViewModel.setDialogStudent("Sidorov");
            deleteStudentViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }
}
