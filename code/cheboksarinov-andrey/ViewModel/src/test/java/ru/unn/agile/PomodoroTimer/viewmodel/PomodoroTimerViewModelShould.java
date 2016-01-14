package ru.unn.agile.PomodoroTimer.viewmodel;

import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.pomodoro.MockObservableTimer;
import ru.unn.agile.pomodoro.SessionManager;
import ru.unn.agile.pomodoro.SessionTimeManager;

import static org.junit.Assert.*;
import static ru.unn.agile.PomodoroTimer.viewmodel.PomodoroTimerViewModel.*;

public class PomodoroTimerViewModelShould {
    private PomodoroTimerViewModel pomodoroTimerViewModel;
    private SessionTimeManager sessionTimeManager;
    private SessionManager sessionManager;
    private MockObservableTimer mockObservableTimer;

    @Before
    public void setUp() {
        mockObservableTimer = new MockObservableTimer();
        sessionTimeManager = new SessionTimeManager();
        sessionManager = new SessionManager(sessionTimeManager, mockObservableTimer);
        pomodoroTimerViewModel = new PomodoroTimerViewModel(sessionManager);
    }
    @Test
    public void haveDefaultValueOnStart() {
        assertTrue(isDefaultValuesOnViewModel(pomodoroTimerViewModel));
    }
    @Test
    public void changeCurrentStatusOnPomodoroWhenStartPomodoroAndOneSecondLeft() {
        sessionManager.startNewPomodoro();
        mockObservableTimer.throwTicks(1);

        assertEquals(Status.POMODORO.toString(), pomodoroTimerViewModel.getCurrentStatus());
    }
    @Test
    public void changeSecondsNumberWhenStartPomodoroAndOneSecondLeft() {
        String expectedSecondsNumber = "59";

        sessionManager.startNewPomodoro();
        mockObservableTimer.throwTicks(1);

        assertEquals(expectedSecondsNumber, pomodoroTimerViewModel.getSeconds());
    }
    @Test
    public void changeMinutesNumberWhenStartPomodoroAndOneSecondLeft() {
        String expectedMinutesNumber = "24";

        sessionManager.startNewPomodoro();
        mockObservableTimer.throwTicks(1);

        assertEquals(expectedMinutesNumber, pomodoroTimerViewModel.getMinutes());
    }
    @Test
    public void changeCurrentStatusOnBreakWhenPomodoroIsOver() {
        sessionManager.startNewPomodoro();
        completePomodoro();

        assertEquals(Status.BREAK.toString(), pomodoroTimerViewModel.getCurrentStatus());
    }
    @Test
    public void changeSecondsNumberToDefaultBreakSecondsWhenPomodoroIsOver() {
        String defaultBreakSeconds = "00";

        sessionManager.startNewPomodoro();
        completePomodoro();

        assertEquals(defaultBreakSeconds, pomodoroTimerViewModel.getSeconds());
    }
    @Test
    public void changeMinutesNumberToDefaultBreakMinutesWhenPomodoroIsOver() {
        String defaultBreakMinutes = "05";

        sessionManager.startNewPomodoro();
        completePomodoro();

        assertEquals(defaultBreakMinutes, pomodoroTimerViewModel.getMinutes());
    }
    @Test
    public void changePomodoroToOneWhenOnePomodoroOver() {
        sessionManager.startNewPomodoro();
        completePomodoro();

        assertEquals("1", pomodoroTimerViewModel.getPomodoroCount());
    }
    @Test
    public void changeStatusOnWaitingWhenPomodoroAndBreakIsOver() {
        sessionManager.startNewPomodoro();
        completePomodoro();
        completeBreak();

        assertEquals(Status.WAITING.toString(), pomodoroTimerViewModel.getCurrentStatus());
    }
    @Test
    public void changeCurrentStatusOnBigBreakWhenFourPomodorosIsOver() {
        sessionManager.startNewPomodoro();
        completeFourPomodoros();

        assertEquals(Status.BIG_BREAK.toString(), pomodoroTimerViewModel.getCurrentStatus());
    }
    @Test
    public void changeSecondsNumberToDefaultBigBreakSecondsWhenFourPomodorosIsOver() {
        String defaultBreakSeconds = "00";

        sessionManager.startNewPomodoro();
        completeFourPomodoros();

        assertEquals(defaultBreakSeconds, pomodoroTimerViewModel.getSeconds());
    }
    @Test
    public void changeMinutesNumberToDefaultBigBreakMinutesWhenFourPomodorosIsOver() {
        String defaultBreakMinutes = "30";

        sessionManager.startNewPomodoro();
        completeFourPomodoros();

        assertEquals(defaultBreakMinutes, pomodoroTimerViewModel.getMinutes());
    }
    @Test
    public void changeCanStartTimerOnFalseWhenStartPomodoroAndOneSecondLeft() {
        sessionManager.startNewPomodoro();
        mockObservableTimer.throwTicks(1);

        assertFalse(pomodoroTimerViewModel.getCanStartTimer());
    }
    private boolean isDefaultValuesOnViewModel(final PomodoroTimerViewModel
                                                       pomodoroTimerViewModel) {
        return  pomodoroTimerViewModel.getCurrentStatus().equals(
                Status.WAITING.toString())
                && pomodoroTimerViewModel.getMinutes().equals("00")
                && pomodoroTimerViewModel.getPomodoroCount().equals("0")
                && pomodoroTimerViewModel.getSeconds().equals("00");
    }
    private void completeFourPomodoros() {
        for (int pomodoroCounter = 0; pomodoroCounter < 3; pomodoroCounter++) {
            completePomodoro();
            completeBreak();
            sessionManager.startNewPomodoro();
        }
        completePomodoro();
    }
    private void completePomodoro() {
        mockObservableTimer.throwTicks(60 * 25 + 1);
    }
    private void completeBreak() {
        mockObservableTimer.throwTicks(60 * 5 + 1);
    }
}
