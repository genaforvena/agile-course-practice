package ru.unn.agile.PomodoroTimer.viewmodel;

import ru.unn.agile.pomodoro.EventGenerator;
import ru.unn.agile.pomodoro.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimerViewModel extends EventGenerator {
    private String seconds;
    private String minutes;
    private String currentStatus;
    private Boolean canStartTimer;
    private String pomodoroCount;
    private final SessionManager sessionManager;
    private final ActionEvent viewModelChangedActionEvent;

    public PomodoroTimerViewModel(final SessionManager sessionManager) {
        seconds = "00";
        minutes = "00";
        currentStatus = Status.WAITING.toString();
        pomodoroCount = "0";
        canStartTimer = true;
        viewModelChangedActionEvent = new ActionEvent(this, 0, "View model is changed");
        this.sessionManager = sessionManager;
        this.sessionManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                currentStatus = sessionManager.getStatus();
                pomodoroCount = String.valueOf(sessionManager.getPomodoroCount());
                seconds = String.valueOf(sessionManager.getPomodoroTime().getSecondCount());
                minutes = String.valueOf(sessionManager.getPomodoroTime().getMinuteCount());
                canStartTimer = true;
                if (canStartTimer != isCurrentStatusWaiting()) {
                    canStartTimer = false;
                }
                fireActionPerformed(viewModelChangedActionEvent);
            }
        });
    }

    public Boolean getCanStartTimer() {
        return canStartTimer;
    }
    public String getPomodoroCount() {
        return pomodoroCount;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getSeconds() {
        return formatTimeString(seconds);
    }


    public String getMinutes() {
        return formatTimeString(minutes);
    }

    private boolean isStringContainOneCharNumber(final String stringWithNumber) {
        return stringWithNumber.length() < 2;
    }

    public void startSession() {
        sessionManager.startNewPomodoro();
    }

    private boolean isCurrentStatusWaiting() {
        return currentStatus.equals(Status.WAITING.toString());
    }

    private String formatTimeString(final String timeValue) {
        if (isStringContainOneCharNumber(timeValue)) {
            return String.format("0%1$d", Integer.parseInt(timeValue));
        }
        return timeValue;
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
