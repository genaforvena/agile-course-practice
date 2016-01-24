package ru.unn.agile.Queue.View;

import ru.unn.agile.Queue.Infrastructure.QueueLogger;
import ru.unn.agile.Queue.ViewModel.LabQueueViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public final class QueueManager {
    private JPanel mainPanel;
    private JButton pushElementButton;
    private JButton findElementButton;
    private JButton popElementButton;
    private JFormattedTextField resultText;
    private JFormattedTextField elementInputText;
    private JLabel resultLabel;
    private JLabel dataInputLabel;
    private JLabel sizeLabel;
    private JTextField sizeText;
    private JLabel headElementLabel;
    private JTextField headElementText;
    private JList<String> listForQueue;
    private JList<String> listForLogger;
    private JPanel listPanel;
    private JLabel listLabel;
    private JPanel loggerPanel;
    private LabQueueViewModel viewModel;

    private QueueManager() { }

    private QueueManager(final LabQueueViewModel viewModel) {
        this.viewModel = viewModel;
        listForQueue = new JList<>(viewModel.getQueueAsArray());
        listForLogger = new JList<>();
        JScrollPane scrollForList = new JScrollPane();
        scrollForList.getViewport().setView(listForQueue);
        JScrollPane scrollForLogger = new JScrollPane();
        scrollForLogger.getViewport().setView(listForLogger);

        loggerPanel.setLayout(new BorderLayout());
        listPanel.setLayout(new BorderLayout());
        loggerPanel.add(scrollForLogger);
        listPanel.add(scrollForList);

        pushElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                viewModel.pushElement();
                bindFromViewModel();
            }
        });

        findElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                viewModel.findElement();
                bindFromViewModel();
            }
        });

        popElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                viewModel.popElement();
                bindFromViewModel();
            }
        });
        bindFromViewModel();
    }

    private void bindFromViewModel() {
        elementInputText.setText(viewModel.getElement());
        resultText.setText(viewModel.getResult());
        headElementText.setText(viewModel.getHeadElement());
        sizeText.setText(Integer.toString(viewModel.getSize()));
        findElementButton.setEnabled(viewModel.isFindButtonEnabled());
        popElementButton.setEnabled(viewModel.isPopButtonEnabled());
        listForQueue.setListData(viewModel.getQueueAsArray());

        List<String> loggersRecords = viewModel.getAllRecords();
        String[] records = loggersRecords.toArray(new String[loggersRecords.size()]);
        listForLogger.setListData(records);
    }

    private void backBindToViewModel() {
        viewModel.setElement(elementInputText.getText());
        viewModel.setResult(resultText.getText());
     }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("QueueManager");
        QueueLogger logger = new QueueLogger("./view_log.txt");
        LabQueueViewModel viewModel = new LabQueueViewModel(logger);

        frame.setContentPane(new QueueManager(viewModel).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
