package ru.unn.agile.MarksAccounting.viewmodel;

import ru.unn.agile.MarksAccounting.model.Group;
import ru.unn.agile.MarksAccounting.model.TableOfMarks;
import java.text.ParseException;

public class AddGroupDialogViewModel extends AddingDialogViewModel {
    public AddGroupDialogViewModel(final TableOfMarks currentTableOfMarks) {
        setDialogDateTextBoxVisible(false);
        setDialogGroupBoxVisible(false);
        setDialogStudentBoxVisible(false);
        setDialogSubjectBoxVisible(false);
        setDialogInputTextBoxVisible(true);
        setDialogType(DialogType.ADD_GROUP);
        setTableOfMarks(currentTableOfMarks);
        setDialogFieldsByDefault();
    }

    @Override
    protected void addObjectToTableOfMarks() throws ParseException {
        getTableOfMarks().addGroup(new Group(getDialogInputTextBox()));
    }
}
