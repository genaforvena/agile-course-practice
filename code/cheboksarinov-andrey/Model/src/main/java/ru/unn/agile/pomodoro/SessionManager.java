package ru.unn.agile.pomodoro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionManager  extends EventGenerator implements ActionListener {
    static final int POMODORO_MINUTE_COUNT = 25;
    static final int BREAK_MINUTE_COUNT = 5;
    static final int BIG_BREAK_MINUTE_COUNT = 30;
    private int pomodoroCount;
    private String status;
    private final ActionEvent timeChangedEvent;
    private final IObservableTimer internalTimer;
    private final SessionTimeManager sessionTimeManager;

    public SessionManager(final SessionTimeManager sessionTimeManager,
                          final IObservableTimer internalTimer) {
        this.sessionTimeManager = sessionTimeManager;
        this.internalTimer = internalTimer;
        timeChangedEvent = new ActionEvent(this, 0, "Time changed");
        setStatus("Waiting");
        sessionTimeManager.setTime(POMODORO_MINUTE_COUNT, 0);
    }

    public void startNewPomodoro() {
        sessionTimeManager.setTime(POMODORO_MINUTE_COUNT, 0);
        setStatus("Pomodoro");
        internalTimer.addTickActionListener(this);
        internalTimer.start();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        updateSessionTime();
        fireActionPerformed(timeChangedEvent);
    }

    public int getPomodoroCount() {
        return pomodoroCount;
    }
    public PomodoroTime getPomodoroTime() {
        return sessionTimeManager.getTime();
    }
    public String getStatus() {
        return status;
    }

    private void setStatus(final String inStatus) {
        status = inStatus;
    }

    private void setRestTimer() {
        final int pomodorosForBigBreak = 4;
        if (isItTimeForBigBreak(pomodorosForBigBreak)) {
            setBigBreakTimer(BIG_BREAK_MINUTE_COUNT);
            setStatus("Big break");
        } else {
            setBreakTimer(BREAK_MINUTE_COUNT);
            setStatus("Break");
        }
    }

    private void setBreakTimer(final int breakMinuteCount) {
        sessionTimeManager.setTime(breakMinuteCount, 0);
        internalTimer.start();
    }

    private void setBigBreakTimer(final int bigBreakMinuteCount) {
        sessionTimeManager.setTime(bigBreakMinuteCount, 0);
        internalTimer.start();
    }

    private boolean isItTimeForBigBreak(final int pomodorosForBigBreak) {
        return pomodoroCount % pomodorosForBigBreak == 0;
    }

    private void updateSessionTime() {
        calculateNextSecond();
        if (sessionTimeManager.getTime().getSecondCount() >= 0) {
            return;
        }
        calculateNextMinute();
        if (sessionTimeManager.getTime().getMinuteCount() >= 0) {
            return;
        }
        if ("Pomodoro".equals(status)) {
            internalTimer.stop();
            addOnePomodoroToday();
            setRestTimer();
            return;
        }
        setWaitStatus();
        internalTimer.stop();
    }

    private void calculateNextSecond() {
        sessionTimeManager.setTime(sessionTimeManager.getTime().getMinuteCount(),
                sessionTimeManager.getTime().getSecondCount() - 1);
    }

    private void calculateNextMinute() {
        final int secondWhenNewMinuteStarted = 59;
        sessionTimeManager.setTime(sessionTimeManager.getTime().getMinuteCount() - 1,
                secondWhenNewMinuteStarted);
    }

    private void setWaitStatus() {
        sessionTimeManager.setTime(0, 0);
        setStatus("Waiting");
    }

    private void addOnePomodoroToday() {
        pomodoroCount += 1;
    }
}
