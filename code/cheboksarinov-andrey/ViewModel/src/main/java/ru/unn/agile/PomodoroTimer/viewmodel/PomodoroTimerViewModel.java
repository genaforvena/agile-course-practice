package ru.unn.agile.PomodoroTimer.viewmodel;

import ru.unn.agile.pomodoro.SessionManager;

import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PomodoroTimerViewModel implements ActionListener {
    private  String seconds;
    private  String minutes;
    private  String currentStatus;
    private  String pomodoroCount;
    private  Boolean canStartTimer;
    private final SessionManager sessionManager;
    private final EventListenerList pomodoroInfoListeners = new EventListenerList();

    public PomodoroTimerViewModel(final SessionManager sessionManager) {
        seconds = "00";
        minutes = "00";
        currentStatus = Status.WAITING.toString();
        pomodoroCount = "0";
        canStartTimer = true;
        this.sessionManager = sessionManager;
        this.sessionManager.addActionListener(this);
    }

    public void setPomodoroCount(final String pomodoroCount) {
        this.pomodoroCount = pomodoroCount;
    }

    public void setCurrentStatus(final String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void setSeconds(final String seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(final String minutes) {
        this.minutes = minutes;
    }

    public boolean getCanStartTimer() {
        return canStartTimer;
    }

    public String getPomodoroCount() {
        return pomodoroCount;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public String getSeconds() {
        if (seconds.length() < 2 && !"00".equals(seconds)) {
            return String.format("0%1$d", Integer.parseInt(seconds));
        }
        return seconds;
    }

    public String getMinutes() {
        if (minutes.length() < 2 && !"00".equals(minutes)) {
            return String.format("0%1$d", Integer.parseInt(minutes));
        }
        return minutes;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        currentStatus = sessionManager.getStatus();
        pomodoroCount = String.valueOf(sessionManager.getPomodoroCount());
        seconds = String.valueOf(sessionManager.getPomodoroTime().getSecondCount());
        minutes = String.valueOf(sessionManager.getPomodoroTime().getMinuteCount());
        canStartTimer = true;
        if (!isCurrentStatusWaiting()) {
            canStartTimer = false;
        }
        fireViewModelChangedPerformed(new ActionEvent(this, 0, "View model is changed"));
    }
    public void startSession() {
        sessionManager.startNewPomodoro();
    }

    public void addPomodoroTimerViewModelChangeListener(final ActionListener listener) {
        pomodoroInfoListeners.add(ActionListener.class, listener);
    }

    public void removePomodoroTimerViewModelChangeListener(final ActionListener listener) {
        pomodoroInfoListeners.remove(ActionListener.class, listener);
    }

    private boolean isCurrentStatusWaiting() {
        return currentStatus.equals(Status.WAITING.toString());
    }

    private void fireViewModelChangedPerformed(final ActionEvent e) {
        Object[] listeners = pomodoroInfoListeners.getListenerList();

        for (int pomodoroTimerInfoListenerCount = listeners.length - 1;
             pomodoroTimerInfoListenerCount >= 0;
             pomodoroTimerInfoListenerCount -= 2) {
            if (listeners[pomodoroTimerInfoListenerCount].equals(ActionListener.class)) {
                ((ActionListener) listeners[pomodoroTimerInfoListenerCount + 1]).actionPerformed(e);
            }
        }
    }
}

enum Status {
    WAITING("Waiting"),
    POMODORO("Pomodoro"),
    BREAK("Break"),
    BIG_BREAK("Big break");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
