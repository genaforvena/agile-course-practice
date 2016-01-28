package ru.unn.agile.MarksAccounting.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.MarksAccounting.model.Group;
import ru.unn.agile.MarksAccounting.model.GroupAlreadyExistsException;
import ru.unn.agile.MarksAccounting.model.TableOfMarks;

import java.text.ParseException;

public class AddGroupDialogViewModelTests {
    private DialogViewModel addGroupViewModel;
    private TableOfMarks tableOfMarks;

    @Before
    public void setUp() {
        initModel();
        initTable();
    }

    @After
    public void tearDown() {
        addGroupViewModel = null;
        tableOfMarks = null;
    }

    private void initModel() {
        TableOfMarks tempTableOfMarks = TestDataInitializer.initTableOfMarks();
        addGroupViewModel = new AddGroupDialogViewModel(tempTableOfMarks);
    }

    private void initTable() {
        tableOfMarks = TestDataInitializer.initTableOfMarks();
    }

    @Test
    public void canSetInitialValues() {
        assertFalse(addGroupViewModel.isDialogGroupBoxVisible());
        assertFalse(addGroupViewModel.isDialogStudentBoxVisible());
        assertFalse(addGroupViewModel.isDialogSubjectBoxVisible());
        assertFalse(addGroupViewModel.isDialogDateTextBoxVisible());
        assertTrue(addGroupViewModel.isDialogInputTextBoxVisible());
        assertTrue(addGroupViewModel.getDialogGroup().isEmpty());
        assertTrue(addGroupViewModel.getDialogStudent().isEmpty());
        assertTrue(addGroupViewModel.getDialogSubject().isEmpty());
        assertTrue(addGroupViewModel.getDialogDate().isEmpty());
        assertTrue(addGroupViewModel.getDialogInputTextBox().isEmpty());
        assertEquals(DialogType.ADD_GROUP, addGroupViewModel.getDialogType());
    }

    @Test
    public void canAddGroup() {
        try {
            tableOfMarks.addGroup(new Group("4"));

            addGroupViewModel.setDialogType(DialogType.ADD_GROUP);
            addGroupViewModel.setDialogInputTextBox("4");
            addGroupViewModel.changeTableOfMarks();

            assertEquals(tableOfMarks, addGroupViewModel.getTableOfMarks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = GroupAlreadyExistsException.class)
    public void canNotAddGroupWhenGroupExists() {
        try {
            addGroupViewModel.setDialogType(DialogType.ADD_GROUP);
            addGroupViewModel.setDialogInputTextBox("1");
            addGroupViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = GroupAlreadyExistsException.class)
    public void canNotAddGroupWhenGroupWithoutSpacesExists() {
        try {
            addGroupViewModel.setDialogInputTextBox("   1 ");
            addGroupViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }

    @Test(expected = InputIsEmptyException.class)
    public void canNotAddGroupWhenInputIsEmptyOrSpaces() {
        try {
            addGroupViewModel.setDialogInputTextBox("   ");
            addGroupViewModel.changeTableOfMarks();
        } catch (ParseException e) {
            fail();
        }
    }
}
