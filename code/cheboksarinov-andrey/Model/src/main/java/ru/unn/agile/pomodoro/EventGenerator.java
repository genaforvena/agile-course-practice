package ru.unn.agile.pomodoro;

import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventGenerator {
    private final EventListenerList listeners = new EventListenerList();

    public void addActionListener(final ActionListener listener) {
        listeners.add(ActionListener.class, listener);
    }

    public void removeActionListener(final ActionListener listener) {
        listeners.remove(ActionListener.class, listener);
    }

    protected void fireActionPerformed(final ActionEvent e) {
        Object[] listenersObject = listeners.getListenerList();

        for (int i = listenersObject.length - 2; i >= 0; i -= 2) {
            if (listenersObject[i] == ActionListener.class) {
                ((ActionListener) listenersObject[i + 1]).actionPerformed(e);
            }
        }
    }
}
