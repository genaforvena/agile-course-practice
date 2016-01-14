package ru.unn.agile.pomodoro;

import java.awt.event.ActionListener;

public class MockObservableTimer implements IObservableTimer {
    private ActionListener tickListener;

    @Override
    public void start() {
        final int mock = 1;
    }

    @Override
    public void stop() {
        final int mock = 1;
    }

    @Override
    public void addTickActionListener(final ActionListener tickListener) {
        this.tickListener = tickListener;
    }

    public void throwTicks(final int tickNumber) {
        for (int i = 0; i < tickNumber; i++) {
            tickListener.actionPerformed(null);
        }
    }


}

