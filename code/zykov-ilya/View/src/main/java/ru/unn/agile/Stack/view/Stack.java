package ru.unn.agile.Stack.view;

import ru.unn.agile.Stack.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class Stack {
    private final ViewModel viewModel;

    private JPanel mainPanel;
    private JList stackValues;
    private JButton buttonPop;
    private JButton buttonPush;
    private JTextField textFieldPush;

    private Stack(final ViewModel viewModel) {
        this.viewModel = viewModel;

        buttonPush.addActionListener(e -> {
            backBind();
            viewModel.pressPushButton();
            bind();
        });

        buttonPop.addActionListener(e -> {
            backBind();
            viewModel.pressPopButton();
            bind();
        });

        textFieldPush.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent documentEvent) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(final DocumentEvent documentEvent) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(final DocumentEvent documentEvent) {
                backBind();
                bind();
            }
        });


        bind();
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Stack");
        frame.setContentPane(new Stack(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void bind() {
        buttonPush.setEnabled(viewModel.isPushButtonEnabled());
        buttonPop.setEnabled(viewModel.isPopButtonEnabled());
        stackValues.setListData(viewModel.getStackAsList().toArray());
    }

    private void backBind() {
        viewModel.setTextFieldPush(textFieldPush.getText());
    }
}
