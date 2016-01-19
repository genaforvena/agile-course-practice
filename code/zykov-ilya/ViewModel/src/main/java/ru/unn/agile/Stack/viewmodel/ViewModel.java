package ru.unn.agile.Stack.viewmodel;

import ru.unn.agile.Stack.Model.Stack;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewModel {
    private String textFieldPush;
    private final Stack stack;

    private boolean pushButtonEnabled;
    private boolean popButtonEnabled;

    public  ViewModel() {
        textFieldPush = "";
        pushButtonEnabled = false;
        pushButtonEnabled = false;
        stack = new Stack();
    }

    public String getTextFieldPush() {
        return textFieldPush;
    }

    public void setTextFieldPushValue(final String textFieldPush) {
        this.textFieldPush = textFieldPush;
        pushButtonEnabled = !textFieldPush.isEmpty();
    }

    public ArrayList getStackAsList() {
        return stack.toArrayList();
    }

    public boolean isPushButtonEnabled() {
        return pushButtonEnabled;
    }

    public boolean isPopButtonEnabled() {
        return popButtonEnabled;
    }

    public void pressPushButton() {
        stack.push(textFieldPush);
        popButtonEnabled = true;
    }

    public void pressPopButton() {
        stack.pop();

        if (stack.isEmpty()) {
            popButtonEnabled = false;
        }
    }

    public String[] getStackAsStringArray() {
        Object[] array = getStackAsList().toArray();
        return Arrays.copyOf(array, array.length, String[].class);
    }
}
