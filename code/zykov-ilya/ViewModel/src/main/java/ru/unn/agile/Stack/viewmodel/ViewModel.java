package ru.unn.agile.Stack.viewmodel;

import ru.unn.agile.Stack.Model.Stack;

import java.util.ArrayList;

public class ViewModel {
    private String textFieldPush;
    private final Stack stack;

    private boolean pushButtonEnabled = false;
    private boolean popButtonEnabled = false;

    public  ViewModel() {
        textFieldPush = "";
        stack = new Stack();
    }

    public String getTextFieldPush() {
        return textFieldPush;
    }

    public void setTextFieldPush(final String textFieldPush) {
        if ("".equals(textFieldPush)) {
            pushButtonEnabled = false;
            return;
        }

        this.textFieldPush = textFieldPush;
        pushButtonEnabled = true;
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

        if (stack.peak() == null) {
            popButtonEnabled = false;
        }
    }
}
