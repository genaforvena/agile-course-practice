package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ViewModel {
    private File currentFile;
    private String currentGroup;
    private String currentSubject;

    private TableOfMarks tableOfMarks;
    private DefaultTableModel tableModel;

    private boolean dialogIsActive;

    private DialogType dialogType;
    private String dialogGroup;
    private String dialogStudent;
    private String dialogSubject;
    private String dialogDate;
    private String dialogInputTextBox;

    private boolean dialogGroupBoxVisible;
    private boolean dialogStudentBoxVisible;
    private boolean dialogSubjectBoxVisible;
    private boolean dialogDateTextBoxVisible;
    private boolean dialogInputTextBoxVisible;

    public boolean isDialogInputTextBoxVisible() {
        return dialogInputTextBoxVisible;
    }

    public boolean isDialogGroupBoxVisible() {
        return dialogGroupBoxVisible;
    }

    public boolean isDialogStudentBoxVisible() {
        return dialogStudentBoxVisible;
    }

    public boolean isDialogSubjectBoxVisible() {
        return dialogSubjectBoxVisible;
    }

    public boolean isDialogDateTextBoxVisible() {
        return dialogDateTextBoxVisible;
    }

    public void activateDialog() {
        dialogIsActive = true;
    }

    public void returnedToMainForm() {
        dialogIsActive = false;
    }

    public ComboBoxModel<String> getGroupComboBoxModel() {
        return new JComboBox<>(this.getGroups()).getModel();
    }

    public ComboBoxModel<String> getSubjectComboBoxModel() {
        return new JComboBox<>(this.getSubjects()).getModel();
    }

    public ComboBoxModel<String> getStudentComboBoxModel() {
        return new JComboBox<>(this.getStudentsForCB()).getModel();
    }

    public TableOfMarks getTableOfMarks() {
        return tableOfMarks;
    }

    public void setDialogGroup(final String dialogGroup) {
        this.dialogGroup = dialogGroup;
    }

    public void setDialogStudent(final String dialogStudent) {
        this.dialogStudent = dialogStudent;
    }

    public void setDialogSubject(final String dialogSubject) {
        this.dialogSubject = dialogSubject;
    }

    public void setDialogDate(final String dialogDate) {
        this.dialogDate = dialogDate;
    }

    public void setCurrentGroup(final String currentGroup) {
        this.currentGroup = currentGroup;
    }

    public void setCurrentSubject(final String currentSubject) {
        this.currentSubject = currentSubject;
    }

    public void setDialogType(final DialogType dialogType) {
        this.dialogType = dialogType;
        setDialogFields();
    }

    private void setDialogFields() {
        switch (dialogType) {
            case ADD_GROUP:
                dialogDateTextBoxVisible = false;
                dialogGroupBoxVisible = false;
                dialogStudentBoxVisible = false;
                dialogSubjectBoxVisible = false;
                dialogInputTextBoxVisible = true;
                break;
            case ADD_STUDENT:
                dialogDateTextBoxVisible = false;
                dialogGroupBoxVisible = true;
                dialogStudentBoxVisible = false;
                dialogSubjectBoxVisible = false;
                dialogInputTextBoxVisible = true;
                break;
            case ADD_SUBJECT:
                dialogDateTextBoxVisible = false;
                dialogGroupBoxVisible = true;
                dialogStudentBoxVisible = false;
                dialogSubjectBoxVisible = false;
                dialogInputTextBoxVisible = true;
                break;
            case ADD_MARK:
                dialogDateTextBoxVisible = true;
                dialogGroupBoxVisible = true;
                dialogStudentBoxVisible = true;
                dialogSubjectBoxVisible = true;
                dialogInputTextBoxVisible = true;
                break;
            case DELETE_GROUP:
                dialogDateTextBoxVisible = false;
                dialogGroupBoxVisible = true;
                dialogStudentBoxVisible = false;
                dialogSubjectBoxVisible = false;
                dialogInputTextBoxVisible = false;
                break;
            case DELETE_STUDENT:
                dialogDateTextBoxVisible = false;
                dialogGroupBoxVisible = true;
                dialogStudentBoxVisible = true;
                dialogSubjectBoxVisible = false;
                dialogInputTextBoxVisible = false;
                break;
            case DELETE_SUBJECT:
                dialogDateTextBoxVisible = false;
                dialogGroupBoxVisible = true;
                dialogStudentBoxVisible = false;
                dialogSubjectBoxVisible = true;
                dialogInputTextBoxVisible = false;
                break;
            case DELETE_MARK:
                dialogDateTextBoxVisible = true;
                dialogGroupBoxVisible = true;
                dialogStudentBoxVisible = true;
                dialogSubjectBoxVisible = true;
                dialogInputTextBoxVisible = false;
                break;
            default:
                setAllInvisible();
                break;
        }
    }

    public void setTableOfMarks(final TableOfMarks tableOfMarks) {
        this.tableOfMarks = tableOfMarks;
    }

    private void setAllInvisible() {
        dialogDateTextBoxVisible = false;
        dialogGroupBoxVisible = false;
        dialogStudentBoxVisible = false;
        dialogSubjectBoxVisible = false;
        dialogInputTextBoxVisible = false;
    }

    public void setDialogInputTextBox(final String dialogInputTextBox) {
        this.dialogInputTextBox = dialogInputTextBox;
    }

    public DefaultTableModel getTableModel() {
        changeTableModel();
        return tableModel;
    }

    private void changeTableModel() {
        Student[] students = this.getStudents();
        GregorianCalendar[] dates = this.getDates(students);

        if (students.length != 0) {
            tableModel = new DefaultTableModel(students.length + 1, dates.length + 1);
            tableModel.setValueAt("Students", 0, 0);
            for (int i = 1; i < students.length + 1; i++) {
                tableModel.setValueAt(students[i - 1], i, 0);
            }
            for (int i = 1; i < dates.length + 1; i++) {
                tableModel.setValueAt(formatDate(dates[i - 1]), 0, i);
            }
            for (int i = 1; i < students.length + 1; i++) {
                for (int j = 1; j < dates.length + 1; j++) {
                    try {
                        tableModel.setValueAt(students[i - 1].getMark(currentSubject,
                                dates[j - 1]).toString(), i, j);
                    } catch (MarkDoesNotExistException e) {
                        tableModel.setValueAt("", i, j);
                    }
                }
            }
            tableModel.fireTableStructureChanged();
            tableModel.fireTableDataChanged();
        }
    }

    private Student[] getStudents() {
        String temp;
        if (dialogIsActive) {
            temp = dialogGroup;
        } else {
            temp = currentGroup;
        }
        try {
            Object[] studentsArray = tableOfMarks.getStudents(new Group(temp)).toArray();
            Student[] result = new Student[studentsArray.length];
            for (int i = 0; i < studentsArray.length; i++) {
                result[i] = (Student) studentsArray[i];
            }
            return result;
        } catch (GroupDoesNotExistException e) {
            return new Student[0];
        }
    }

    private String[] getStudentsForCB() {
        Student[] students = this.getStudents();
        String[] result = new String[students.length];
        for (int i = 0; i < students.length; i++) {
            result[i] = students[i].getName();
        }
        return result;
    }

    private GregorianCalendar[] getDates(final Student[] students) {
        ArrayList<GregorianCalendar> temp = formTempCalendar(students);
        GregorianCalendar[] result = new GregorianCalendar[temp.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = temp.get(i);
        }
        return result;
    }

    private ArrayList<GregorianCalendar> formTempCalendar(final Student[] students) {
        ArrayList<GregorianCalendar> temp = new ArrayList<GregorianCalendar>();
        int size = -1;
        for (int i = 1; i < students.length; i++) {
            int k = 0;
            for (int j = 0; j < students[i].getMarks().size(); j++) {
                if (size == -1) {
                    temp.add(students[i].getMarks().get(j).getDate());
                    size = 1;
                } else {
                    while (k < temp.size()
                            && temp.get(k).before(students[i].getMarks().get(j).getDate())) {
                        k++;
                    }
                    if (k == temp.size()
                            || !temp.get(k).equals(students[i].getMarks().get(j).getDate())) {
                        temp.add(students[i].getMarks().get(j).getDate());
                    }
                }
            }
        }
        return temp;
    }

    public ViewModel() {
        currentGroup = "";
        currentSubject = "";
        tableOfMarks = new TableOfMarks();
        tableModel = new DefaultTableModel(0, 0);
        dialogIsActive = false;
    }

    public boolean change() {
        if ("@Mark".equals(dialogInputTextBox) || "@Group".equals(dialogInputTextBox)
                || "@Subjects".equals(dialogInputTextBox)
                || "@Student".equals(dialogInputTextBox)) {
            return false;
        }
        switch (dialogType) {
            case ADD_GROUP:
                if (!inputIsEmpty()) {
                    return addGroup();
                }
                break;
            case ADD_STUDENT:
                if (!inputIsEmpty()) {
                    return addStudent();
                }
                break;
            case ADD_SUBJECT:
                if (!inputIsEmpty()) {
                    return addSubject();
                }
                break;
            case ADD_MARK:
                if (!inputIsEmpty()) {
                    return addMark();
                }
                break;
            case DELETE_GROUP:
                return deleteGroup();
            case DELETE_STUDENT:
                return deleteStudent();
            case DELETE_SUBJECT:
                return deleteSubject();
            case DELETE_MARK:
                return deleteMark();
            default:
                return false;
        }
        return false;
    }

    private boolean addGroup() {
        try {
            tableOfMarks.addGroup(new Group(dialogInputTextBox));
            return true;
        } catch (GroupAlreadyExistsException e) {
            return false;
        }
    }

    private boolean addStudent() {
        try {
            tableOfMarks.addStudent(new Group(dialogGroup),
                    new Student(dialogInputTextBox));
            return true;
        } catch (NullPointerException e) {
            return false;
        } catch (GroupDoesNotExistException e) {
            return false;
        } catch (StudentAlreadyExistsException e) {
            return false;
        }
    }

    private boolean addSubject() {
        try {
            tableOfMarks.addAcademicSubject(new Group(dialogGroup),
                    dialogInputTextBox);
            return true;
        } catch (NullPointerException e) {
            return false;
        } catch (GroupDoesNotExistException e) {
            return false;
        } catch (SubjectAlreadyExistsException e) {
            return false;
        }
    }

    private boolean addMark() {
        for (int i = 0; i < dialogInputTextBox.length(); i++) {
            if (dialogInputTextBox.charAt(i) < '0' || dialogInputTextBox.charAt(i) > '9') {
                return false;
            }
        }
        int tempMark;
        try {
            tempMark = Integer.parseInt(dialogInputTextBox);
        } catch (NumberFormatException e) {
            return false;
        }
        GregorianCalendar tempDate = parseDate(dialogDate);
        if (tempDate.equals(new GregorianCalendar(0, 0, 0))) {
            return false;
        }
        try {
            tableOfMarks.addNewMark(new Mark(tempMark, dialogSubject, tempDate),
                    new Student(dialogStudent), new Group(dialogGroup));
            return true;
        } catch (AcademicSubjectDoesNotExistException e) {
            return false;
        } catch (GroupDoesNotExistException e) {
            return false;
        } catch (StudentDoesNotExistException e) {
            return false;
        } catch (NoMarkCorrectionException e) {
            return false;
        } catch (MarkIsNotPositiveException e) {
            return false;
        }
    }

    private boolean deleteGroup() {
        try {
            tableOfMarks.deleteGroup(new Group(dialogGroup));
            return true;
        } catch (GroupDoesNotExistException e) {
            return false;
        }
    }

    private boolean deleteStudent() {
        try {
            tableOfMarks.deleteStudent(new Group(dialogGroup), new Student(dialogStudent));
            return true;
        } catch (GroupDoesNotExistException e) {
            return false;
        } catch (StudentDoesNotExistException e) {
            return false;
        }
    }

    private boolean deleteSubject() {
        try {
            tableOfMarks.deleteAcademicSubject(new Group(dialogGroup), dialogSubject);
            return true;
        } catch (GroupDoesNotExistException e) {
            return false;
        } catch (AcademicSubjectDoesNotExistException e) {
            return false;
        }
    }

    private boolean deleteMark() {
        GregorianCalendar tempDate = parseDate(dialogDate);
        if (tempDate.equals(new GregorianCalendar(0, 0, 0))) {
            return false;
        }
        try {
            tableOfMarks.deleteMark(new Group(dialogGroup), new Student(dialogStudent),
                    dialogSubject, tempDate);
            return true;
        } catch (AcademicSubjectDoesNotExistException e) {
            return false;
        } catch (GroupDoesNotExistException e) {
            return false;
        } catch (StudentDoesNotExistException e) {
            return false;
        } catch (MarkDoesNotExistException e) {
            return false;
        }
    }

    public GregorianCalendar parseDate(final String strDate) {
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

    private boolean inputIsEmpty() {
        char[] temp = dialogInputTextBox.toCharArray();
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != ' ') {
                return false;
            }
        }
        return true;
    }

    private int getNumberOfGroups() {
        return tableOfMarks.getGroups().size();
    }

    private String[] getGroups() {
        String[] result = new String[getNumberOfGroups()];
        for (int i = 0; i < this.getNumberOfGroups(); i++) {
            result[i] = tableOfMarks.getGroups().get(i).getNumber();
        }
        return result;
    }

    private String[] getSubjects() {
        String[] result = new String[getNumberOfSubjects()];
        String temp;
        if (dialogIsActive) {
            temp = dialogGroup;
        } else {
            temp = currentGroup;
        }
        for (int i = 0; i < this.getNumberOfSubjects(); i++) {
            result[i] = tableOfMarks.getAcademicSubjects(new Group(temp)).get(i);
        }
        return result;
    }

    private int getNumberOfSubjects() {
        try {
            if (dialogIsActive) {
                return tableOfMarks.getAcademicSubjects(new Group(dialogGroup)).size();
            }
            return tableOfMarks.getAcademicSubjects(new Group(currentGroup)).size();
        } catch (GroupDoesNotExistException e) {
            return 0;
        }
    }

    public void save() {
        try {
            if (currentFile == null) {
                saveAs();
            } else {
                FileWriter fw = new FileWriter(currentFile);
                if (!writeFile(fw)) {
                    throw new IOException();
                }
                fw.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't save file!");
        }
    }

    public void saveAs() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                if (!currentFile.exists()) {
                    currentFile.createNewFile();
                }
                FileWriter fw = new FileWriter(currentFile);
                if (!writeFile(fw)) {
                    throw new IOException();
                }
                fw.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't save file!");
        }
    }

    public boolean writeFile(final FileWriter fw) {
        try {
            ArrayList<Group> tempGroups = tableOfMarks.getGroups();
            for (int i = 0; i < tempGroups.size(); i++) {
                fw.write("@Group\n");
                fw.write(tempGroups.get(i).getNumber());
                fw.write("\n");
                if (!writeSubjects(fw, tempGroups.get(i))) {
                    return false;
                }
                if (!writeStudents(fw, tempGroups.get(i))) {
                    return false;
                }
                fw.write("@EndOfGroup\n");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean writeSubjects(final FileWriter fw, final Group group) {
        try {
            fw.write("@Subjects\n");
            for (int i = 0; i < group.getAcademicSubjects().size(); i++) {
                fw.write(group.getAcademicSubjects().get(i));
                fw.write("\n");
            }
            fw.write("@EndOfSubjects\n");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean writeStudents(final FileWriter fw, final Group group) {
        try {
            for (int i = 0; i < group.getStudents().size(); i++) {
                fw.write("@Student\n");
                fw.write(group.getStudents().get(i).getName());
                fw.write("\n");
                if (!writeMarks(fw, group.getStudents().get(i))) {
                    return false;
                }
                fw.write("@EndOfStudent\n");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean writeMarks(final FileWriter fw, final Student student) {
        try {
            for (int i = 0; i < student.getMarks().size(); i++) {
                fw.write("@Mark\n");
                fw.write(student.getMarks().get(i).getAcademicSubject());
                fw.write("\n");
                fw.write(formatDate(student.getMarks().get(i).getDate()));
                fw.write("\n");
                fw.write(Integer.toString(student.getMarks().get(i).getValue()));
                fw.write("\n");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String formatDate(final GregorianCalendar date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        fmt.setCalendar(date);
        return fmt.format(date.getTime());
    }

    public void open() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                FileReader fr = new FileReader(currentFile);
                if (!readFile(fr)) {
                    throw new IOException();
                }
                fr.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't open file!");
        }
    }

    public boolean readFile(final FileReader fr) {
        tableOfMarks = new TableOfMarks();
        try {
            Group currentGroup;
            while (fr.ready()) {
                currentGroup = readGroup(fr);
                if (currentGroup.equals(new Group(""))) {
                    throw new IOException();
                }
                if (!readSubjects(fr, currentGroup)) {
                    throw new IOException();
                }
                if (!readStudents(fr, currentGroup)) {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            tableOfMarks = new TableOfMarks();
            return false;
        }
        return true;
    }

    private Group readGroup(final FileReader fr) {
        String tempString;
        if (readLine(fr).equals("@Group")) {
            tempString = readLine(fr);
            try {
                tableOfMarks.addGroup(new Group(tempString));
                return new Group(tempString);
            } catch (GroupAlreadyExistsException e) {
                return new Group("");
            }
        }
        return new Group("");
    }

    private boolean readSubjects(final FileReader fr, final Group currentGroup) {
        if ("@Subjects".equals(readLine(fr))) {
            String tempString = readLine(fr);
            while (!"@EndOfSubjects".equals(tempString)) {
                if ("@Mark".equals(tempString)  || "@Student".equals(tempString)
                        || "@Group".equals(tempString)  || "@Subjects".equals(tempString)) {
                    return false;
                }
                try {
                    tableOfMarks.addAcademicSubject(currentGroup, tempString);
                } catch (GroupDoesNotExistException e) {
                    return false;
                } catch (SubjectAlreadyExistsException e) {
                    return false;
                }
                tempString = readLine(fr);
            }
            return true;
        }
        return false;
    }

    private boolean readStudents(final FileReader fr, final Group currentGroup) {
        String tempString = readLine(fr);
        Student currentStudent;
        while (!"@EndOfGroup".equals(tempString)) {
            if ("@Student".equals(tempString)) {
                currentStudent = new Student(readLine(fr));
                tableOfMarks.addStudent(currentGroup, currentStudent);
                readMarks(fr, currentGroup, currentStudent);
                tempString = readLine(fr);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean readMarks(final FileReader fr, final Group currentGroup,
                              final Student currentStudent) {
        String tempString = readLine(fr);
        while (!"@EndOfStudent".equals(tempString)) {
            if (!"@Mark".equals(tempString)) {
                return false;
            }
            if (!readMark(fr, currentGroup, currentStudent)) {
                return false;
            }
            tempString = readLine(fr);
        }
        return true;
    }

    private boolean readMark(final FileReader fr, final Group currentGroup,
                             final Student currentStudent) {
        try {
            String tempSubject = readLine(fr);
            GregorianCalendar tempDate = parseDate(readLine(fr));
            if (tempDate.equals(new GregorianCalendar(0, 0, 0))) {
                return false;
            }
            int tempValue = Integer.parseInt(readLine(fr));
            tableOfMarks.addNewMark(new Mark(tempValue, tempSubject, tempDate),
                    currentStudent, currentGroup);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (GroupDoesNotExistException e) {
            return false;
        } catch (StudentDoesNotExistException e) {
            return false;
        } catch (AcademicSubjectDoesNotExistException e) {
            return false;
        } catch (NoMarkCorrectionException e) {
            return false;
        } catch (MarkIsNotPositiveException e) {
            return false;
        }
    }

    private String readLine(final FileReader fr) {
        String tempString = "";
        try {
            int tmp = fr.read();
            while ((char) tmp != '\n') {
                tempString += (char) tmp;
                tmp = fr.read();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't open file!");
        }
        return tempString;
    }

}
