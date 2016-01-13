package ru.unn.agile.PomodoroTimer.viewmodel;

import ru.unn.agile.pomodoro.EventGenerator;
import ru.unn.agile.pomodoro.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimerViewModel extends EventGenerator {
    private String seconds;
    private String minutes;
    private String currentStatus;
    private String pomodoroCount;
    private final SessionManager sessionManager;
    private final ActionEvent viewModelChangedActionEvent;

    public PomodoroTimerViewModel(final SessionManager sessionManager) {
        seconds = "00";
        minutes = "00";
        currentStatus = Status.WAITING.toString();
        pomodoroCount = "0";
        viewModelChangedActionEvent = new ActionEvent(this, 0, "View model is changed");
        this.sessionManager = sessionManager;
        this.sessionManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                currentStatus = sessionManager.getStatus();
                pomodoroCount = String.valueOf(sessionManager.getPomodoroCount());
                seconds = String.valueOf(sessionManager.getPomodoroTime().getSecondCount());
                minutes = String.valueOf(sessionManager.getPomodoroTime().getMinuteCount());
                fireActionPerformed(viewModelChangedActionEvent);
            }
        });
    }

    public String getPomodoroCount() {
        return pomodoroCount;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getSeconds() {
        if (isStringContainOneCharNumber(seconds)) {
            return String.format("0%1$d", Integer.parseInt(seconds));
        }
        return seconds;
    }

    private boolean isStringContainOneCharNumber(final String stringWithNumber) {
        return stringWithNumber.length() < 2;
    }

    public String getMinutes() {
        if (isStringContainOneCharNumber(minutes)) {
            return String.format("0%1$d", Integer.parseInt(minutes));
        }
        return minutes;
    }

    public void startSession() {
        sessionManager.startNewPomodoro();
    }

    private boolean isCurrentStatusWaiting() {
        return currentStatus.equals(Status.WAITING.toString());
    }

    public enum Status {
        WAITING("Waiting"),
        POMODORO("Pomodoro"),
        BREAK("Break"),
        BIG_BREAK("Big break");

        private final String name;

        Status(final String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }
    }
}
