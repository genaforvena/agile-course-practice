package ru.unn.agile.Stack.view;

import javax.swing.*;

public class Stack {
    private JPanel mainPanel;
    private JList stackValues;
    private JButton buttonPop;
    private JButton buttonPush;
    private JTextField textFieldPush;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stack");
        frame.setContentPane(new Stack().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
