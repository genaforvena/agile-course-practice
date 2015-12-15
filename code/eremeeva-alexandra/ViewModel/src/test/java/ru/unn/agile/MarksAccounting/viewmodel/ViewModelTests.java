package ru.unn.agile.MarksAccounting.viewmodel;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.unn.agile.MarksAccounting.model.Group;
import ru.unn.agile.MarksAccounting.model.Mark;
import ru.unn.agile.MarksAccounting.model.Student;
import ru.unn.agile.MarksAccounting.model.TableOfMarks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ViewModelTests {

    private ViewModel viewModel;
    private TableOfMarks tableOfMarks;

    @Before
    public void setUp() {
        initModel();
        initTable();
    }

    @After
    public void tearDown() {
        viewModel = null;
        tableOfMarks = null;
    }

    private void initModel() {
        viewModel = new ViewModel();
        TableOfMarks tempTableOfMarks = new TableOfMarks();
        tempTableOfMarks.addGroup(new Group("1"));
        tempTableOfMarks.addGroup(new Group("2"));
        tempTableOfMarks.addGroup(new Group("3"));
        tempTableOfMarks.addAcademicSubject(new Group("1"), "Maths");
        tempTableOfMarks.addAcademicSubject(new Group("1"), "History");
        tempTableOfMarks.addAcademicSubject(new Group("2"), "Science");
        tempTableOfMarks.addStudent(new Group("1"), new Student("Sidorov"));
        tempTableOfMarks.addStudent(new Group("1"), new Student("Petrov"));
        tempTableOfMarks.addStudent(new Group("3"), new Student("Ivanov"));
        tempTableOfMarks.addNewMark(new Mark(4, "Maths", parseDate("10-05-2015")),
                new Student("Sidorov"), new Group("1"));
        viewModel.setTableOfMarks(tempTableOfMarks);
    }

    private void initTable() {
        tableOfMarks = new TableOfMarks();
        tableOfMarks.addGroup(new Group("1"));
        tableOfMarks.addGroup(new Group("2"));
        tableOfMarks.addGroup(new Group("3"));
        tableOfMarks.addAcademicSubject(new Group("1"), "Maths");
        tableOfMarks.addAcademicSubject(new Group("1"), "History");
        tableOfMarks.addAcademicSubject(new Group("2"), "Science");
        tableOfMarks.addStudent(new Group("1"), new Student("Sidorov"));
        tableOfMarks.addStudent(new Group("1"), new Student("Petrov"));
        tableOfMarks.addStudent(new Group("3"), new Student("Ivanov"));
        tableOfMarks.addNewMark(new Mark(4, "Maths", parseDate("10-05-2015")),
                new Student("Sidorov"), new Group("1"));
    }

    private GregorianCalendar parseDate(final String strDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            return new GregorianCalendar(0, 0, 0);
        }
        GregorianCalendar tempDate = new GregorianCalendar();
        tempDate.setTime(date);
        return tempDate;
    }

    @Test
    public void testParseDate() {
        GregorianCalendar date = parseDate("10-05-2015");
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        fmt.setCalendar(date);
        assertEquals("10-05-2015", fmt.format(date.getTime()));
    }

    @Test
    public void canGetGroupComboBox() {
        String[] temp = new String[3];
        temp[0] = "1";
        temp[1] = "2";
        temp[2] = "3";
        ComboBoxModel<String> trueComboBoxModel = new JComboBox<String>(temp).getModel();
        ComboBoxModel comboBoxModel = viewModel.getGroupComboBoxModel();
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            assertEquals(trueComboBoxModel.getElementAt(i), comboBoxModel.getElementAt(i));
        }
    }

    @Test
    public void canGetSubjectComboBox() {
        String[] temp = new String[2];
        temp[0] = "History";
        temp[1] = "Maths";
        ComboBoxModel<String> trueComboBoxModel = new JComboBox<String>(temp).getModel();
        viewModel.setCurrentGroup("1");
        ComboBoxModel comboBoxModel = viewModel.getSubjectComboBoxModel();
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            assertEquals(trueComboBoxModel.getElementAt(i), comboBoxModel.getElementAt(i));
        }
    }

    @Test
    public void canGetSubjectComboBoxWhenDialogIsActive() {
        String[] temp = new String[2];
        temp[0] = "History";
        temp[1] = "Maths";
        ComboBoxModel<String> trueComboBoxModel = new JComboBox<String>(temp).getModel();
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        ComboBoxModel comboBoxModel = viewModel.getSubjectComboBoxModel();
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            assertEquals(trueComboBoxModel.getElementAt(i), comboBoxModel.getElementAt(i));
        }
    }

    @Test
    public void canGetStudentComboBox() {
        String[] temp = new String[2];
        temp[0] = "Petrov";
        temp[1] = "Sidorov";
        ComboBoxModel<String> trueComboBoxModel = new JComboBox<String>(temp).getModel();
        viewModel.setCurrentGroup("1");
        ComboBoxModel comboBoxModel = viewModel.getStudentComboBoxModel();
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            assertEquals(trueComboBoxModel.getElementAt(i), comboBoxModel.getElementAt(i));
        }
    }

    @Test
    public void canGetStudentComboBoxWhenDialogIsActive() {
        String[] temp = new String[2];
        temp[0] = "Petrov";
        temp[1] = "Sidorov";
        ComboBoxModel<String> trueComboBoxModel = new JComboBox<String>(temp).getModel();
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        ComboBoxModel comboBoxModel = viewModel.getStudentComboBoxModel();
        for (int i = 0; i < comboBoxModel.getSize(); i++) {
            assertEquals(trueComboBoxModel.getElementAt(i), comboBoxModel.getElementAt(i));
        }
    }

    @Test
    public void canGetTableModel() {
        DefaultTableModel trueTableModel = new DefaultTableModel(3, 2);
        trueTableModel.setValueAt("Students", 0, 0);
        trueTableModel.setValueAt("Petrov", 1, 0);
        trueTableModel.setValueAt("Sidorov", 2, 0);
        trueTableModel.setValueAt("10-5-2015", 0, 1);
        trueTableModel.setValueAt("1", 1, 1);
        trueTableModel.setValueAt("", 2, 1);
        DefaultTableModel tableModel = viewModel.getTableModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                assertEquals(trueTableModel.getValueAt(i, j), tableModel.getValueAt(i, j));
            }
        }
    }

    @Test
    public void canAddGroup() {
        viewModel.setDialogType(DialogType.ADD_GROUP);
        viewModel.activateDialog();
        viewModel.setDialogInputTextBox("4");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.addGroup(new Group("4"));
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotAddGroupWhenGroupExists() {
        viewModel.setDialogType(DialogType.ADD_GROUP);
        viewModel.activateDialog();
        viewModel.setDialogInputTextBox("1");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddWhenInputIsEmpty() {
        viewModel.setDialogType(DialogType.ADD_GROUP);
        viewModel.activateDialog();
        viewModel.setDialogInputTextBox("   ");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canAddSubject() {
        viewModel.setDialogType(DialogType.ADD_SUBJECT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogInputTextBox("Science");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.addAcademicSubject(new Group("1"), "Science");
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotAddSubjectWhenGroupDoesNotExist() {
        viewModel.setDialogType(DialogType.ADD_SUBJECT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("");
        viewModel.setDialogInputTextBox("Science");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddSubjectWhenSubjectExists() {
        viewModel.setDialogType(DialogType.ADD_SUBJECT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogInputTextBox("Maths");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canAddStudent() {
        viewModel.setDialogType(DialogType.ADD_STUDENT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogInputTextBox("Smirnov");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.addStudent(new Group("1"), new Student("Smirnov"));
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotAddStudentWhenGroupDoesNotExist() {
        viewModel.setDialogType(DialogType.ADD_STUDENT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("");
        viewModel.setDialogInputTextBox("Smirnov");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddStudentWhenStudentExists() {
        viewModel.setDialogType(DialogType.ADD_STUDENT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogInputTextBox("Sidorov");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canAddMark() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("11-11-2015");
        viewModel.setDialogInputTextBox("5");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.addNewMark(new Mark(5, "Maths", parseDate("11-11-2015")),
                new Student("Sidorov"), new Group("1"));
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotAddMarkWhenGroupDoesNotExist() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("11-11-2015");
        viewModel.setDialogInputTextBox("5");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddMarkWhenSubjectDoesNotExist() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("");
        viewModel.setDialogDate("11-11-2015");
        viewModel.setDialogInputTextBox("5");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddMarkWhenStudentDoesNotExist() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("11-11-2015");
        viewModel.setDialogInputTextBox("5");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddMarkWhenMarkAlreadyExists() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("10-05-2015");
        viewModel.setDialogInputTextBox("5");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddMarkWhenCanNotParseDate() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("11/11/2015");
        viewModel.setDialogInputTextBox("5");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddMarkWhenCanNotParseValue() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("11-11-2015");
        viewModel.setDialogInputTextBox("4abc");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotAddMarkWhenValueIsNotPositive() {
        viewModel.setDialogType(DialogType.ADD_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("11-11-2015");
        viewModel.setDialogInputTextBox("-2");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canDeleteGroup() {
        viewModel.setDialogType(DialogType.DELETE_GROUP);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.deleteGroup(new Group("1"));
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotDeleteGroupWhenGroupDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_GROUP);
        viewModel.activateDialog();
        viewModel.setDialogGroup("");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canDeleteSubject() {
        viewModel.setDialogType(DialogType.DELETE_SUBJECT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogSubject("Maths");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.deleteAcademicSubject(new Group("1"), "Maths");
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotDeleteSubjectWhenGroupDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_SUBJECT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("");
        viewModel.setDialogSubject("Maths");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotDeleteSubjectWhenSubjectDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_SUBJECT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogSubject("");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canDeleteStudent() {
        viewModel.setDialogType(DialogType.DELETE_STUDENT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.deleteStudent(new Group("1"), new Student("Sidorov"));
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotDeleteStudentWhenGroupDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_STUDENT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("");
        viewModel.setDialogStudent("Sidorov");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotDeleteStudentWhenSubjectDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_STUDENT);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canDeleteMark() {
        viewModel.setDialogType(DialogType.DELETE_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("10-05-2015");
        viewModel.change();
        viewModel.returnedToMainForm();
        tableOfMarks.deleteMark(new Group("1"), new Student("Sidorov"),
                "Maths", parseDate("10-05-2015"));
        assertEquals(tableOfMarks, viewModel.getTableOfMarks());
    }

    @Test
    public void canNotDeleteMarkWhenGroupDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("10-05-2015");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotDeleteMarkWhenSubjectDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("");
        viewModel.setDialogDate("10-05-2015");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotDeleteMarkWhenStudentDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("10-05-2015");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotDeleteMarkWhenMarkDoesNotExist() {
        viewModel.setDialogType(DialogType.DELETE_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("10-06-2015");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canNotDeleteMarkWhenCanNotParseDate() {
        viewModel.setDialogType(DialogType.DELETE_MARK);
        viewModel.activateDialog();
        viewModel.setDialogGroup("1");
        viewModel.setDialogStudent("Sidorov");
        viewModel.setDialogSubject("Maths");
        viewModel.setDialogDate("10/05/2015");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

    @Test
    public void canSaveTable() {
        try {
            File file = new File("TestTable.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            boolean temp = viewModel.writeFile(fw);
            fw.close();
            assertTrue(temp);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    @Test
    public void canOpenFile() {
        try {
            File file = new File("TestTable.txt");
            FileReader fr = new FileReader(file);
            boolean temp = viewModel.readFile(fr);
            fr.close();
            assertTrue(temp);
        } catch (IOException e) {
            assertTrue(false);
        }
    }
    @Test
    public void canNotOpenWrongFile() {
        try {
            File file = new File("WrongTestFile.txt");
            FileReader fr = new FileReader(file);
            boolean temp = viewModel.readFile(fr);
            fr.close();
            assertFalse(temp);
        } catch (IOException e) {
            assertTrue(false);
        }
    }

    @Test
    public void canNotChangeWhenInputIsWrong() {
        viewModel.setDialogType(DialogType.ADD_GROUP);
        viewModel.activateDialog();
        viewModel.setDialogInputTextBox("@Group");
        boolean temp = viewModel.change();
        viewModel.returnedToMainForm();
        assertFalse(temp);
    }

}
