package ru.unn.agile.pomodoro;

public class SessionTimeManager {
    private final PomodoroTime pomodoroTime;

    public SessionTimeManager() {
        pomodoroTime = new PomodoroTime();
    }

    public void setTime(final int minute, final int second) {
        pomodoroTime.setMinuteCount(minute);
        pomodoroTime.setSecondCount(second);
    }

    public PomodoroTime getTime() {
        return pomodoroTime;
    }
}
