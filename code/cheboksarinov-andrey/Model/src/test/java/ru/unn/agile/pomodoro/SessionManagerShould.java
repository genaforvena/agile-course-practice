package ru.unn.agile.pomodoro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionManagerShould {
    private SessionManager sessionManager;
    private MockObservableTimer mockTimer;
    private SessionTimeManager sessionTimeManager;

    @Before
    public void setUp() {
        mockTimer = new MockObservableTimer();
        sessionTimeManager = new SessionTimeManager();
        sessionManager = new SessionManager(sessionTimeManager, mockTimer);
    }

    @Test
    public void decreaseSecondByOneIfPomodoroStartAndOneSecondLeft() {
        sessionManager.startNewPomodoro();
        final int secondCountAfterOneTick = 59;

        mockTimer.throwTicks(1);

        assertEquals(secondCountAfterOneTick, sessionTimeManager.getTime().getSecondCount());
    }

    @Test
    public void decreaseMinuteByOneIfPomodoroStartAndOneMinuteLeft() {
        sessionManager.startNewPomodoro();
        final int minuteCountAfterOneLeft = 24;
        tickOneMinute();

        assertEquals(minuteCountAfterOneLeft, sessionTimeManager.getTime().getMinuteCount());
    }

    @Test
    public void changeStatusToBreakWhenPomodoroIsOver() {
        sessionManager.startNewPomodoro();

        completePomodoro();

        assertEquals("Break", sessionManager.getStatus());
    }

    @Test
    public void secondsSetOnZeroWhenPomodoroIsOverAndNotTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completePomodoro();

        assertEquals(0, sessionTimeManager.getTime().getSecondCount());
    }

    @Test
    public void minutesSetOnFiveWhenPomodoroIsOverAndNotTimeForBigBreak() {
        sessionManager.startNewPomodoro();
        final int minuteCountForBreak = 5;

        completePomodoro();

        assertEquals(minuteCountForBreak, sessionTimeManager.getTime().getMinuteCount());
    }

    @Test
    public void addOneCompletedPomodoroWhenItIsOver() {
        sessionManager.startNewPomodoro();

        completePomodoro();

        assertEquals(1, sessionManager.getPomodoroCount());
    }

    @Test
    public void changeStatusToWaitingWhenPomodoroAndRestAreOver() {
        sessionManager.startNewPomodoro();

        completePomodoro();
        completeRest();

        assertEquals("Waiting", sessionManager.getStatus());
    }

    @Test
    public void secondsSetOnZeroWhenPomodoroIsOverAndTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completeFourPomodoros();

        assertEquals(0, sessionTimeManager.getTime().getSecondCount());
    }

    @Test
    public void minuteSetOnThirtyWhenPomodoroIsOverAndTimeForBigBreak() {
        sessionManager.startNewPomodoro();
        final int minuteCountForBigBreak = 30;

        completeFourPomodoros();

        assertEquals(minuteCountForBigBreak, sessionTimeManager.getTime().getMinuteCount());
    }

    @Test
    public void statusSetOnBigBreakWhenPomodoroIsOverAndTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completeFourPomodoros();

        assertEquals("Big break", sessionManager.getStatus());
    }

    private void completeFourPomodoros() {
        completePomodoro();
        completeRest();
        sessionManager.startNewPomodoro();
        completePomodoro();
        completeRest();
        sessionManager.startNewPomodoro();
        completePomodoro();
        completeRest();
        sessionManager.startNewPomodoro();
        completePomodoro();
    }

    private void completeRest() {
        mockTimer.throwTicks(60 * 5 + 1);
    }


    private void completePomodoro() {
        mockTimer.throwTicks(60 * 25 + 1);
    }

    private void tickOneMinute() {
        mockTimer.throwTicks(60);
    }
}
