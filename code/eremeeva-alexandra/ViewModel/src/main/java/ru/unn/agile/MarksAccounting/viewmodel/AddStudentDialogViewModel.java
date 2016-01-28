package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.*;
import javax.swing.*;
import java.text.ParseException;

public class AddStudentDialogViewModel extends AddingDialogViewModel {
    public AddStudentDialogViewModel(final TableOfMarks currentTableOfMarks) {
        setDialogDateTextBoxVisible(false);
        setDialogGroupBoxVisible(true);
        setDialogStudentBoxVisible(false);
        setDialogSubjectBoxVisible(false);
        setDialogInputTextBoxVisible(true);
        setDialogType(DialogType.ADD_STUDENT);
        setTableOfMarks(currentTableOfMarks);
        setDialogFieldsByDefault();
    }

    @Override
    public ComboBoxModel<String> getDialogGroupsComboBoxModel() {
        return getComboBoxModelOfAllGroups();
    }

    @Override
    protected void addObjectToTableOfMarks() throws ParseException {
        getTableOfMarks().addStudent(new Group(getDialogGroup()),
                new Student(getDialogInputTextBox()));
    }
}
