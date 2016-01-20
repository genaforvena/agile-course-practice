package ru.unn.agile.MarksAccounting.view;

import ru.unn.agile.MarksAccounting.viewmodel.DialogType;
import ru.unn.agile.MarksAccounting.viewmodel.MainFormViewModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public final class TableOfMarks {
    private MainFormViewModel mainFormViewModel;
    private JPanel mainPanel;
    private JComboBox<String> groupsComboBox;
    private JComboBox<String> subjectsComboBox;
    private JLabel groupLabel;
    private JLabel subjectLabel;
    private JPanel auxiliaryPanel;
    private JPanel dataPanel;
    private JScrollPane scrollTable;
    private JComboBox<String> changingComboBox;
    private JButton changingButton;
    private JTable dataTable;
    private JPanel changingPanel;

    private TableOfMarks() { }

    private TableOfMarks(final MainFormViewModel mainFormViewModel) {
        this.mainFormViewModel = mainFormViewModel;
        backBind();
        changingComboBox.setModel(mainFormViewModel.getChangingComboBoxModel());

        changingButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                bind();
                makeDialog(DialogType.getType(changingComboBox.getSelectedItem().toString()));
                changingComboBox.setModel(
                        TableOfMarks.this.mainFormViewModel.getChangingComboBoxModel());
                groupsComboBox.setModel(
                        TableOfMarks.this.mainFormViewModel.getGroupComboBoxModel());
                subjectsComboBox.setModel(
                        TableOfMarks.this.mainFormViewModel.getSubjectComboBoxModel());
                backBind();
            }
        });

        groupsComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    bind();
                    subjectsComboBox.setModel(
                            TableOfMarks.this.mainFormViewModel.getSubjectComboBoxModel());
                    backBind();
                }
            }
        });

        subjectsComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    bind();
                    backBind();
                }
            }
        });
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Table of marks");
        frame.setContentPane(new TableOfMarks(new MainFormViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        mainFormViewModel.setGroupInCurrentTable(groupsComboBox.getSelectedItem());
        mainFormViewModel.setSubjectInCurrentTable(subjectsComboBox.getSelectedItem());
    }

    private void backBind() {
        DefaultTableModel tableModel;
        tableModel = mainFormViewModel.getTableModel();
        dataTable.setModel(tableModel);
        dataTable.setTableHeader(null);
    }

    private void makeDialog(final DialogType dialogType) {
        ChangingDialog changingDialog = new ChangingDialog(mainFormViewModel.getTableOfMarks(),
                dialogType);
        changingDialog.pack();
        changingDialog.setVisible(true);
    }

    private void createUIComponents() {
        dataTable = new JTable() {
            @Override
            public boolean isCellEditable(final int rowNumber, final int colNumber) {
                return false;
            }
        };
    }
}
