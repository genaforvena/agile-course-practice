package ru.unn.agile.PercentAccretion.view;

import javax.swing.*;

import ru.unn.agile.PercentAccretion.Model.PercentAccretionFactory;
import ru.unn.agile.PercentAccretion.infrastructure.PercentAccretionLogger;
import ru.unn.agile.PercentAccretion.viewmodel.PercentAccretionViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public final class PercentAccretionView {
    private final PercentAccretionViewModel viewModel;
    private JTextField initialSumTextField;
    private JTextField percentRateTextField;
    private JTextField countOfYearsTextField;
    private JPanel labelPanel;
    private JPanel fieldsPanel;
    private JRadioButton simplePercentRadioButton;
    private JRadioButton complexPercentRadioButton;
    private JLabel initialSumLabel;
    private JLabel percentRateLabel;
    private JLabel timeLabel;
    private JButton calculateSumButton;
    private JPanel resultPanel;
    private JPanel choosePercentPanel;
    private JPanel calculationPanel;
    private JPanel formPanel;
    private JTextField resultTextField;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JList<String> logList;
    private final ButtonGroup radioButtonGroup;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("PercentAccretionView");
        frame.setContentPane(new PercentAccretionView().formPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private PercentAccretionView() {

        viewModel = new PercentAccretionViewModel(new PercentAccretionLogger(
                "./PercentAccretion.xml"));

        radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(simplePercentRadioButton);
        radioButtonGroup.add(complexPercentRadioButton);

        radioButtonGroup.setSelected(simplePercentRadioButton.getModel(), true);
        simplePercentRadioButton.setActionCommand(
                PercentAccretionFactory.AccretionType.SIMPLE_PERCENT_SUM.toString());
        complexPercentRadioButton.setActionCommand(
                PercentAccretionFactory.AccretionType.COMPLEX_PERCENT_SUM.toString());

        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBindPercentAccretionView();
                bindPercentAccretionView();
            }
        };

        simplePercentRadioButton.addActionListener(radioButtonListener);
        complexPercentRadioButton.addActionListener(radioButtonListener);

        FocusAdapter focusLostListener = new FocusAdapter() {
            public void focusLost(final FocusEvent e) {
                backBindPercentAccretionView();
                PercentAccretionView.this.viewModel.focusLost();
                bindPercentAccretionView();
            }
        };

        initialSumTextField.addFocusListener(focusLostListener);
        countOfYearsTextField.addFocusListener(focusLostListener);
        percentRateTextField.addFocusListener(focusLostListener);

        calculateSumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBindPercentAccretionView();
                viewModel.calculateResultSum();
                bindPercentAccretionView();
            }
        });

        bindPercentAccretionView();
    }

    private void bindPercentAccretionView() {
        calculateSumButton.setEnabled(viewModel.isCalculateButtonEnabled());

        statusLabel.setText(viewModel.getStatusMessage());
        resultTextField.setText(viewModel.getResultSum());

        List<String> log = viewModel.getLog();
        String[] items = log.toArray(new String[log.size()]);
        logList.setListData(items);
    }

    private void backBindPercentAccretionView() {
        viewModel.setInitialSum(initialSumTextField.getText());
        viewModel.setPercentRate(percentRateTextField.getText());
        viewModel.setCountOfYears(countOfYearsTextField.getText());
        viewModel.setPercentType(radioButtonGroup.getSelection().getActionCommand());
    }
}
