package ru.unn.agile.pomodoro;

public class PomodoroTime {
    private int second;
    private int minute;

    public PomodoroTime() {
        minute = 0;
        second = 0;
    }
    public int getSecondCount() {
        return second;
    }

    public void setSecondCount(final int second) {
        this.second = second;
    }
    public int getMinuteCount() {
        return minute;
    }
    public void setMinuteCount(final int minute) {
        this.minute = minute;
    }
}
