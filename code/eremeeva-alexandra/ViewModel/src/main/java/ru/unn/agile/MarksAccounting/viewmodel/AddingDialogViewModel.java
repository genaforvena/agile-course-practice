package ru.unn.agile.MarksAccounting.viewmodel;

import java.text.ParseException;

public abstract class AddingDialogViewModel extends DialogViewModel {
    @Override
    public void changeTableOfMarks() throws ParseException {
        removeInitialAndFinalSpacesInInput();
        if (getDialogInputTextBox().isEmpty()) {
            throw new InputIsEmptyException("Input is empty!");
        }
        addObjectToTableOfMarks();
    }

    protected void removeInitialAndFinalSpacesInInput() {
        if (!getDialogInputTextBox().isEmpty()) {
            while (getDialogInputTextBox().charAt(0) == ' '
                    && getDialogInputTextBox().length() > 1) {
                setDialogInputTextBox(getDialogInputTextBox().substring(1));
            }
            int i = getDialogInputTextBox().length() - 1;
            while (getDialogInputTextBox().charAt(i) == ' '
                    && getDialogInputTextBox().length() > 1) {
                setDialogInputTextBox(getDialogInputTextBox().substring(0, i--));
            }
            if (" ".equals(getDialogInputTextBox())) {
                setDialogInputTextBox("");
            }
        }
    }

    protected abstract void addObjectToTableOfMarks() throws ParseException;
}
