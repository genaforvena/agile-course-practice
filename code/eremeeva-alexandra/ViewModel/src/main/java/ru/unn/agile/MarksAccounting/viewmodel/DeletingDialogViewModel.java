package ru.unn.agile.MarksAccounting.viewmodel;

import java.text.ParseException;

public abstract class DeletingDialogViewModel extends DialogViewModel {
    @Override
    public void changeTableOfMarks() throws ParseException {
            deleteObjectFromTableOfMarks();
    }

    protected abstract void deleteObjectFromTableOfMarks() throws ParseException;
}
