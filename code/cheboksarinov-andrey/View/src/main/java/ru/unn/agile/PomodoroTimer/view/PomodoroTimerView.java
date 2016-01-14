package ru.unn.agile.PomodoroTimer.view;

import ru.unn.agile.PomodoroTimer.viewmodel.PomodoroTimerViewModel;
import ru.unn.agile.pomodoro.ObservableTimer;
import ru.unn.agile.pomodoro.SessionManager;
import ru.unn.agile.pomodoro.SessionTimeManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class PomodoroTimerView {
    private JButton startTimerButton;
    private JPanel mainPanel;
    private JLabel minutesLabel;
    private JLabel secondsLabel;
    private JLabel pomodoroCountLabel;
    private JLabel currentStatusLabel;

    private final PomodoroTimerViewModel viewModel;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("PomodoroTimerView");
        SessionManager sessionManager = new SessionManager(new SessionTimeManager(),
                new ObservableTimer());
        PomodoroTimerViewModel viewModel = new PomodoroTimerViewModel(sessionManager);
        frame.setContentPane(new PomodoroTimerView(viewModel).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private PomodoroTimerView(final PomodoroTimerViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
            }
        });

        startTimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewModel.startSession();
                backBind();
            }
        });
    }

    private void backBind() {
        pomodoroCountLabel.setText(viewModel.getPomodoroCount());
        currentStatusLabel.setText(viewModel.getCurrentStatus());
        startTimerButton.setEnabled(viewModel.getCanStartTimer());
        secondsLabel.setText(viewModel.getSeconds());
        minutesLabel.setText(viewModel.getMinutes());
    }
}
