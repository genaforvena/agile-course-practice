package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.Group;
import ru.unn.agile.MarksAccounting.model.TableOfMarks;
import javax.swing.*;
import java.text.ParseException;

public class DeleteSubjectDialogViewModel extends DeletingDialogViewModel {
    public DeleteSubjectDialogViewModel(final TableOfMarks currentTableOfMarks) {
        setDialogDateTextBoxVisible(false);
        setDialogGroupBoxVisible(true);
        setDialogStudentBoxVisible(false);
        setDialogSubjectBoxVisible(true);
        setDialogInputTextBoxVisible(false);
        setDialogType(DialogType.DELETE_SUBJECT);
        setTableOfMarks(currentTableOfMarks);
        setDialogFieldsByDefault();
    }

    @Override
    public ComboBoxModel<String> getDialogGroupsComboBoxModel() {
        String[] groupNumbers = getTableOfMarks().getGroupsWhereSubjectsExistAsArrayOfStrings();
        setDialogGroup(groupNumbers[0]);
        return new JComboBox<String>(groupNumbers).getModel();
    }

    @Override
    public ComboBoxModel<String> getDialogSubjectsComboBoxModel() {
        return getComboBoxModelOfAllSubjects();
    }

    @Override
    protected void deleteObjectFromTableOfMarks() throws ParseException {
        getTableOfMarks().deleteAcademicSubject(new Group(getDialogGroup()),
                getDialogSubject());
    }
}
