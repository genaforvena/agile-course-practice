package ru.unn.agile.Stack.viewmodel;

public class ViewModel {
    private String textFieldPush;
    private String result;

    private boolean pushButtonEnabled = false;

    public  ViewModel() {
        textFieldPush = "";
        result = "";
    }

    public String getTextFieldPush() {
        return textFieldPush;
    }

    public void setTextFieldPush(String textFieldPush) {
        this.textFieldPush = textFieldPush;
        pushButtonEnabled = true;
    }

    public String getResult() {
        return result;
    }

    public boolean isPushButtonEnabled() {
        return pushButtonEnabled;
    }


}
