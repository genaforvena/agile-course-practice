package ru.unn.agile.Stack.view;

import ru.unn.agile.Stack.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class StackView {
    private JPanel mainPanel;
    private JList<String> stackValues;
    private JButton buttonPop;
    private JButton buttonPush;
    private JTextField textFieldPush;
    private final ViewModel viewModel;

    private StackView(final ViewModel viewModel) {
        this.viewModel = viewModel;

        buttonPush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBind();
                viewModel.pressPushButton();
                bind();
            }
        });

        buttonPop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBind();
                viewModel.pressPopButton();
                bind();
            }
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
        JFrame frame = new JFrame("StackView");
        frame.setContentPane(new StackView(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        buttonPush.setEnabled(viewModel.isPushButtonEnabled());
        buttonPop.setEnabled(viewModel.isPopButtonEnabled());
        stackValues.setListData(viewModel.getStackAsStringArray());
    }

    private void backBind() {
        viewModel.setTextFieldPushValue(textFieldPush.getText());
    }
}
