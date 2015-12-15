package ru.unn.agile.MarksAccounting.view;

import ru.unn.agile.MarksAccounting.viewmodel.DialogType;
import ru.unn.agile.MarksAccounting.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ChangingDialog extends JDialog {
    private final ViewModel viewModel;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> groupComboBox;
    private JComboBox<String> studentComboBox;
    private JComboBox<String> subjectComboBox;
    private JTextField inputTextField;
    private final JFormattedTextField dateField;
    private JLabel dialogTitle;
    private JLabel dateLabel;
    private JLabel subjectLabel;
    private JLabel studentLabel;
    private JLabel groupLabel;
    private JPanel inputPanel;
    private JPanel datePanel;

    public ChangingDialog(final ViewModel viewModel, final DialogType typeOfDialog) {
        dialogTitle.setText(typeOfDialog.getTypeValue());
        this.viewModel = viewModel;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        final DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        dateField = new JFormattedTextField(format);
        datePanel.add(dateField);
        bind();
        groupComboBox.setModel(viewModel.getGroupComboBoxModel());
        bind();
        backBind();

        groupComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    bind();
                    backBind();
                }
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                bind();
                if (ChangingDialog.this.viewModel.change()) {
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Input is not correct");
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                dispose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void bind() {
        viewModel.setDialogType(DialogType.getType(dialogTitle.getText()));
        try {
            viewModel.setDialogGroup(groupComboBox.getSelectedItem().toString());
        } catch (NullPointerException e) {
            viewModel.setDialogGroup("");
        }
        try {
            viewModel.setDialogDate(dateField.getText());
        } catch (NullPointerException e) {
            viewModel.setDialogDate("");
        }
        try {
            viewModel.setDialogStudent(studentComboBox.getSelectedItem().toString());
        } catch (NullPointerException e) {
            viewModel.setDialogStudent("");
        }
        try {
            viewModel.setDialogSubject(subjectComboBox.getSelectedItem().toString());
        } catch (NullPointerException e) {
            viewModel.setDialogSubject("");
        }
        try {
            viewModel.setDialogInputTextBox(inputTextField.getText());
        } catch (NullPointerException e) {
            viewModel.setDialogInputTextBox("");
        }
    }

    private void backBind() {
        groupComboBox.setVisible(viewModel.isDialogGroupBoxVisible());
        studentComboBox.setModel(viewModel.getStudentComboBoxModel());
        studentComboBox.setVisible(viewModel.isDialogStudentBoxVisible());
        subjectComboBox.setModel(viewModel.getSubjectComboBoxModel());
        subjectComboBox.setVisible(viewModel.isDialogSubjectBoxVisible());
        dateField.setVisible(viewModel.isDialogDateTextBoxVisible());
        inputTextField.setVisible(viewModel.isDialogInputTextBoxVisible());
        groupLabel.setVisible(viewModel.isDialogGroupBoxVisible());
        studentLabel.setVisible(viewModel.isDialogStudentBoxVisible());
        subjectLabel.setVisible(viewModel.isDialogSubjectBoxVisible());
        dateLabel.setVisible(viewModel.isDialogDateTextBoxVisible());
    }
}
