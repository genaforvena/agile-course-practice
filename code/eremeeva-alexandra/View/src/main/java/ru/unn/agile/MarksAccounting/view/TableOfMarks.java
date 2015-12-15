package ru.unn.agile.MarksAccounting.view;

import ru.unn.agile.MarksAccounting.viewmodel.DialogType;
import ru.unn.agile.MarksAccounting.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public final class TableOfMarks {
    private ViewModel viewModel;

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
    private JButton saveAsButton;
    private JButton savingButton;
    private JButton openButton;
    private JTable dataTable;
    private JPanel changingPanel;

    private TableOfMarks() { }

    private TableOfMarks(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
        scrollTable.add(dataTable);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                TableOfMarks.this.viewModel.open();
                groupsComboBox.setModel(TableOfMarks.this.viewModel.getGroupComboBoxModel());
                bind();
                subjectsComboBox.setModel(TableOfMarks.this.viewModel.getSubjectComboBoxModel());
                bind();
                backBind();
            }
        });

        savingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                TableOfMarks.this.viewModel.save();
            }
        });

        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                TableOfMarks.this.viewModel.saveAs();
            }
        });

        changingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                makeDialog(DialogType.getType(changingComboBox.getSelectedItem().toString()));
                groupsComboBox.setModel(TableOfMarks.this.viewModel.getGroupComboBoxModel());
                bind();
                subjectsComboBox.setModel(TableOfMarks.this.viewModel.getSubjectComboBoxModel());
                bind();
                backBind();
            }
        });

        groupsComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    bind();
                    subjectsComboBox.setModel(
                            TableOfMarks.this.viewModel.getSubjectComboBoxModel());
                    backBind();
                }
            }
        });

        subjectsComboBox.addItemListener(new ItemListener() {
            @Override
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
        frame.setContentPane(new TableOfMarks(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        try {
            viewModel.setCurrentGroup(groupsComboBox.getSelectedItem().toString());
        } catch (NullPointerException e) {
            viewModel.setCurrentGroup("");
        }
        try {
            viewModel.setCurrentSubject(subjectsComboBox.getSelectedItem().toString());
        } catch (NullPointerException e) {
            viewModel.setCurrentSubject("");
        }
    }

    private void backBind() {
        DefaultTableModel tableModel;
        tableModel = viewModel.getTableModel();
        dataTable.setModel(tableModel);
        tableModel.fireTableStructureChanged();
        tableModel.fireTableDataChanged();
    }

    private void makeDialog(final DialogType dialogType) {
        TableOfMarks.this.viewModel.activateDialog();
        ChangingDialog addGroupDialog = new ChangingDialog(viewModel, dialogType);
        addGroupDialog.pack();
        addGroupDialog.setVisible(true);
        TableOfMarks.this.viewModel.returnedToMainForm();
    }

}
