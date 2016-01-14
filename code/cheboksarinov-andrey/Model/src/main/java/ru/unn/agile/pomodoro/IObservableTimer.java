package ru.unn.agile.pomodoro;

import java.awt.event.ActionListener;

public interface IObservableTimer extends ITimer {

    void addTickActionListener(ActionListener tickListener);
}
