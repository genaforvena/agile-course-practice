package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.Group;
import ru.unn.agile.MarksAccounting.model.Student;
import ru.unn.agile.MarksAccounting.model.TableOfMarks;
import javax.swing.*;
import java.text.ParseException;

public class DeleteStudentDialogViewModel extends DeletingDialogViewModel {
    public DeleteStudentDialogViewModel(final TableOfMarks currentTableOfMarks) {
        setDialogDateTextBoxVisible(false);
        setDialogGroupBoxVisible(true);
        setDialogStudentBoxVisible(true);
        setDialogSubjectBoxVisible(false);
        setDialogInputTextBoxVisible(false);
        setDialogType(DialogType.DELETE_STUDENT);
        setTableOfMarks(currentTableOfMarks);
        setDialogFieldsByDefault();
    }

    @Override
    public ComboBoxModel<String> getDialogGroupsComboBoxModel() {
        String[] groupNumbers = getTableOfMarks().getGroupsWhereStudentsExistAsArrayOfStrings();
        setDialogGroup(groupNumbers[0]);
        return new JComboBox<String>(groupNumbers).getModel();
    }

    @Override
    public ComboBoxModel<String> getDialogStudentsComboBoxModel() {
        return getComboBoxModelOfAllStudents();
    }

    @Override
    protected void deleteObjectFromTableOfMarks() throws ParseException {
        getTableOfMarks().deleteStudent(new Group(getDialogGroup()),
                new Student(getDialogStudent()));
    }
}
