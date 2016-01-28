package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.*;
import javax.swing.*;
import java.text.ParseException;

public class AddMarkDialogViewModel extends AddingDialogViewModel {
    public AddMarkDialogViewModel(final TableOfMarks currentTableOfMarks) {
        setDialogDateTextBoxVisible(true);
        setDialogGroupBoxVisible(true);
        setDialogStudentBoxVisible(true);
        setDialogSubjectBoxVisible(true);
        setDialogInputTextBoxVisible(true);
        setDialogType(DialogType.ADD_MARK);
        setTableOfMarks(currentTableOfMarks);
        setDialogFieldsByDefault();
    }

    @Override
    public ComboBoxModel<String> getDialogGroupsComboBoxModel() {
        String[] groupNumbers = getTableOfMarks().getGroupsWhereCanAddMarksAsArrayOfStrings();
        setDialogGroup(groupNumbers[0]);
        return new JComboBox<String>(groupNumbers).getModel();
    }

    @Override
    public ComboBoxModel<String> getDialogStudentsComboBoxModel() {
        return getComboBoxModelOfAllStudents();
    }

    @Override
    public ComboBoxModel<String> getDialogSubjectsComboBoxModel() {
        return getComboBoxModelOfAllSubjects();
    }

    @Override
    protected void addObjectToTableOfMarks() throws ParseException {
        getTableOfMarks().addNewMark(new Mark(Integer.parseInt(getDialogInputTextBox()),
                        getDialogSubject(), DateParser.parseDate(getDialogDate())),
                new Student(getDialogStudent()), new Group(getDialogGroup()));
    }
}
