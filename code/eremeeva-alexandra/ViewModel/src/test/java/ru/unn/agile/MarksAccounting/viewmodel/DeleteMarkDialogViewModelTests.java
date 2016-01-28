package ru.unn.agile.MarksAccounting.viewmodel;

import org.junit.After;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.MarksAccounting.model.*;
import javax.swing.*;
import java.text.ParseException;
import java.util.GregorianCalendar;

public class DeleteMarkDialogViewModelTests {

    private DialogViewModel deleteMarkViewModel;
    private TableOfMarks tableOfMarks;

    @Before
    public void setUp() {
        initModel();
        initTable();
    }

    @After
    public void tearDown() {
        deleteMarkViewModel = null;
        tableOfMarks = null;
    }

    private void initModel() {
        TableOfMarks tempTableOfMarks = TestDataInitializer.initTableOfMarks();
        deleteMarkViewModel = new DeleteMarkDialogViewModel(tempTableOfMarks);
    }

    private void initTable() {
        tableOfMarks = TestDataInitializer.initTableOfMarks();
    }

    @Test
    public void canSetInitialValues() {
        assertTrue(deleteMarkViewModel.isDialogGroupBoxVisible());
        assertTrue(deleteMarkViewModel.isDialogStudentBoxVisible());
        assertTrue(deleteMarkViewModel.isDialogSubjectBoxVisible());
        assertTrue(deleteMarkViewModel.isDialogDateTextBoxVisible());
        assertFalse(deleteMarkViewModel.isDialogInputTextBoxVisible());
        assertTrue(deleteMarkViewModel.getDialogGroup().isEmpty());
        assertTrue(deleteMarkViewModel.getDialogStudent().isEmpty());
        assertTrue(deleteMarkViewModel.getDialogSubject().isEmpty());
        assertTrue(deleteMarkViewModel.getDialogDate().isEmpty());
        assertTrue(deleteMarkViewModel.getDialogInputTextBox().isEmpty());
        assertEquals(DialogType.DELETE_MARK, deleteMarkViewModel.getDialogType());
    }

    @Test
    public void canGetGroupsComboBoxModel() {
        String[] groupNumbersWhereMarksExist = {"1"};
        ComboBoxModel<String> expectedComboBoxModel = new JComboBox<String>(
                groupNumbersWhereMarksExist).getModel();

        assertTrue(ComboBoxModelsEqualer.comboBoxModelsEqualing(expectedComboBoxModel,
                deleteMarkViewModel.getDialogGroupsComboBoxModel()));
    }

    @Test
    public void canGetStudentsComboBoxModel() {
        String[] studentNamesWhereMarksExistInFirstGroup = {"Sidorov"};
        ComboBoxModel<String> expectedComboBoxModel = new JComboBox<String>(
                studentNamesWhereMarksExistInFirstGroup).getModel();

        deleteMarkViewModel.setDialogGroup("1");

        assertTrue(ComboBoxModelsEqualer.comboBoxModelsEqualing(expectedComboBoxModel,
                deleteMarkViewModel.getDialogStudentsComboBoxModel()));
    }

    @Test
    public void canGetSubjectsComboBoxModel() {
        String[] subjectsWhereMarksExistInFirstGroup = {"Maths"};
        ComboBoxModel<String> expectedComboBoxModel = new JComboBox<String>(
                subjectsWhereMarksExistInFirstGroup).getModel();

        deleteMarkViewModel.setDialogGroup("1");
        deleteMarkViewModel.setDialogStudent("Sidorov");

        assertTrue(ComboBoxModelsEqualer.comboBoxModelsEqualing(expectedComboBoxModel,
                deleteMarkViewModel.getDialogSubjectsComboBoxModel()));
    }

    @Test
    public void canDeleteMark() {
        try {
            tableOfMarks.deleteMark(new Group("1"), new Student("Sidorov"), "Maths",
                    new GregorianCalendar(2015, 4, 10));

            setFieldsForMarkDeleting();
            deleteMarkViewModel.changeTableOfMarks();

            assertEquals(tableOfMarks, deleteMarkViewModel.getTableOfMarks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = GroupDoesNotExistException.class)
    public void canNotDeleteMarkWhenGroupDoesNotExist() {
        try {
            setFieldsForMarkDeleting();
            deleteMarkViewModel.setDialogGroup("116");
            deleteMarkViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = StudentDoesNotExistException.class)
    public void canNotDeleteMarkWhenStudentDoesNotExist() {
        try {
            setFieldsForMarkDeleting();
            deleteMarkViewModel.setDialogStudent("Smirnov");
            deleteMarkViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = AcademicSubjectDoesNotExistException.class)
    public void canNotDeleteMarkWhenSubjectDoesNotExist() {
        try {
            setFieldsForMarkDeleting();
            deleteMarkViewModel.setDialogSubject("Geography");
            deleteMarkViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = MarkDoesNotExistException.class)
    public void canNotDeleteMarkWhenMarkDoesNotExist() {
        try {
            deleteMarkViewModel.setDialogGroup("1");
            deleteMarkViewModel.setDialogStudent("Petrov");
            deleteMarkViewModel.setDialogSubject("History");
            deleteMarkViewModel.setDialogDate("02-2-2015");
            deleteMarkViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    public void canNotDeleteMarkWhenDateFormatIsWrong() {
        try {
            setFieldsForMarkDeleting();
            deleteMarkViewModel.setDialogDate("29-02-2015");
            deleteMarkViewModel.changeTableOfMarks();
            fail();
        } catch (RuntimeException e) {
            fail();
        } catch (ParseException e) {
            assertTrue(true);
        }
    }

    private void setFieldsForMarkDeleting() {
        deleteMarkViewModel.setDialogGroup("1");
        deleteMarkViewModel.setDialogStudent("Sidorov");
        deleteMarkViewModel.setDialogSubject("Maths");
        deleteMarkViewModel.setDialogDate("10-5-2015");
    }
}
