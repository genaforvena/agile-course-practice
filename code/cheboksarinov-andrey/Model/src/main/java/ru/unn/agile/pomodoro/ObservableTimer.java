package ru.unn.agile.pomodoro;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class ObservableTimer implements ITimer, IObservableTimer {
    private javax.swing.Timer swingTimer;
    private ActionListener tickListener;
    @Override
    public void start() {
        swingTimer = new Timer((int) TimeUnit.SECONDS.toMillis(1), tickListener);
        swingTimer.start();
    }

    @Override
    public void stop() {
        swingTimer.stop();
    }

    @Override
    public void addTickActionListener(final ActionListener tickListener) {
        this.tickListener = tickListener;
    }
}
