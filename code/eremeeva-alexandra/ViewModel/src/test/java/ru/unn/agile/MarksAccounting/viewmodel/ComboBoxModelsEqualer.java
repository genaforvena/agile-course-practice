package ru.unn.agile.MarksAccounting.viewmodel;

import javax.swing.*;

public class ComboBoxModelsEqualer {
    private ComboBoxModelsEqualer() { }

    public static boolean comboBoxModelsEqualing(
            final ComboBoxModel<String> expectedComboBoxModel,
            final ComboBoxModel<String> realComboBoxModel) {
        for (int i = 0; i < realComboBoxModel.getSize(); i++) {
            if (!expectedComboBoxModel.getElementAt(i).equals(
                    realComboBoxModel.getElementAt(i))) {
                return false;
            }
        }
        return true;
    }
}
