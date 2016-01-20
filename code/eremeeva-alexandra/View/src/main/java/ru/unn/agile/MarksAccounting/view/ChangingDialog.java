package ru.unn.agile.MarksAccounting.view;

import ru.unn.agile.MarksAccounting.model.TableOfMarks;
import ru.unn.agile.MarksAccounting.viewmodel.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.ParseException;

public class ChangingDialog extends JDialog {
    private DialogViewModel dialogViewModel;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<String> groupComboBox;
    private JComboBox<String> studentComboBox;
    private JComboBox<String> subjectComboBox;
    private JTextField inputTextField;
    private JTextField dateField;
    private JLabel dialogTitle;
    private JLabel dateLabel;
    private JLabel subjectLabel;
    private JLabel studentLabel;
    private JLabel groupLabel;
    private JPanel inputPanel;
    private JPanel datePanel;

    public ChangingDialog(final TableOfMarks tableOfMarks,
                          final DialogType typeOfDialog) {
        dialogTitle.setText(typeOfDialog.getTypeDescription());
        initDialogViewModel(tableOfMarks, typeOfDialog);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        groupComboBox.setModel(dialogViewModel.getDialogGroupsComboBoxModel());
        studentComboBox.setModel(dialogViewModel.getDialogStudentsComboBoxModel());
        backBind();

        groupComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bind();
                    studentComboBox.setModel(dialogViewModel.getDialogStudentsComboBoxModel());
                    backBind();
                }
            }
        });

        studentComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    bind();
                    backBind();
                }
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                bind();
                try {
                    ChangingDialog.this.dialogViewModel.changeTableOfMarks();
                    dispose();
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Mark must be an integer value!");
                } catch (ParseException exception) {
                    JOptionPane.showMessageDialog(null, "Wrong format of date!");
                } catch (RuntimeException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
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
        dialogViewModel.setDialogGroup(groupComboBox.getSelectedItem());
        dialogViewModel.setDialogDate(dateField.getText());
        dialogViewModel.setDialogStudent(studentComboBox.getSelectedItem());
        dialogViewModel.setDialogSubject(subjectComboBox.getSelectedItem());
        dialogViewModel.setDialogInputTextBox(inputTextField.getText());
    }

    private void backBind() {
        groupComboBox.setVisible(dialogViewModel.isDialogGroupBoxVisible());
        studentComboBox.setVisible(dialogViewModel.isDialogStudentBoxVisible());
        subjectComboBox.setModel(dialogViewModel.getDialogSubjectsComboBoxModel());
        subjectComboBox.setVisible(dialogViewModel.isDialogSubjectBoxVisible());
        dateField.setVisible(dialogViewModel.isDialogDateTextBoxVisible());
        inputTextField.setVisible(dialogViewModel.isDialogInputTextBoxVisible());
        groupLabel.setVisible(dialogViewModel.isDialogGroupBoxVisible());
        studentLabel.setVisible(dialogViewModel.isDialogStudentBoxVisible());
        subjectLabel.setVisible(dialogViewModel.isDialogSubjectBoxVisible());
        dateLabel.setVisible(dialogViewModel.isDialogDateTextBoxVisible());
    }

    private void initDialogViewModel(final TableOfMarks tableOfMarks,
                                     final DialogType typeOfDialog) {
        switch (typeOfDialog) {
            case ADD_GROUP:
                dialogViewModel = new AddGroupDialogViewModel(tableOfMarks);
                break;
            case ADD_STUDENT:
                dialogViewModel = new AddStudentDialogViewModel(tableOfMarks);
                break;
            case ADD_SUBJECT:
                dialogViewModel = new AddSubjectDialogViewModel(tableOfMarks);
                break;
            case ADD_MARK:
                dialogViewModel = new AddMarkDialogViewModel(tableOfMarks);
                break;
            case DELETE_GROUP:
                dialogViewModel = new DeleteGroupDialogViewModel(tableOfMarks);
                break;
            case DELETE_STUDENT:
                dialogViewModel = new DeleteStudentDialogViewModel(tableOfMarks);
                break;
            case DELETE_SUBJECT:
                dialogViewModel = new DeleteSubjectDialogViewModel(tableOfMarks);
                break;
            case DELETE_MARK:
                dialogViewModel = new DeleteMarkDialogViewModel(tableOfMarks);
                break;
            default:
                break;
        }
    }
}
