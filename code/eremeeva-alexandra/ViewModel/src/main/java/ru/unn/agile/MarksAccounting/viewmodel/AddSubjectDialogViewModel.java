package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.Group;
import ru.unn.agile.MarksAccounting.model.TableOfMarks;
import javax.swing.*;
import java.text.ParseException;

public class AddSubjectDialogViewModel extends AddingDialogViewModel {
    public AddSubjectDialogViewModel(final TableOfMarks currentTableOfMarks) {
        setDialogDateTextBoxVisible(false);
        setDialogGroupBoxVisible(true);
        setDialogStudentBoxVisible(false);
        setDialogSubjectBoxVisible(false);
        setDialogInputTextBoxVisible(true);
        setDialogType(DialogType.ADD_SUBJECT);
        setTableOfMarks(currentTableOfMarks);
        setDialogFieldsByDefault();
    }

    @Override
    public ComboBoxModel<String> getDialogGroupsComboBoxModel() {
        String[] groupNumbers = getTableOfMarks().getGroupsAsArrayOfStrings();
        setDialogGroup(groupNumbers[0]);
        return new JComboBox<String>(groupNumbers).getModel();
    }

    @Override
    protected void addObjectToTableOfMarks() throws ParseException {
        getTableOfMarks().addAcademicSubject(new Group(getDialogGroup()),
                getDialogInputTextBox());
    }
}
