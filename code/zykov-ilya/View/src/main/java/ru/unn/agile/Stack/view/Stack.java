package ru.unn.agile.Stack.view;

import ru.unn.agile.Stack.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;

public class Stack {
    private ViewModel viewModel;

    private JPanel mainPanel;
    private JList stackValues;
    private JButton buttonPop;
    private JButton buttonPush;
    private JTextField textFieldPush;

    private Stack(final ViewModel viewModel) {
        this.viewModel = viewModel;

        bind();

        buttonPush.addActionListener(e -> {
        });

        buttonPop.addActionListener(e -> {
        });

        textFieldPush.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                backBind();
                bind();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stack");
        frame.setContentPane(new Stack(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        buttonPush.setEnabled(viewModel.isPushButtonEnabled());
    }

    private void backBind() {
        viewModel.setTextFieldPush(textFieldPush.getText());
    }
}
