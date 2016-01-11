package ru.unn.agile.Deque.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DequeTest {
    private Deque<Integer> deque;

    @Before
    public void initializeDeque() {
        deque = new Deque<>();
    }

    @Test
    public void createdDequeWithoutParameterIsNotNull() {
        assertNotNull(deque);
    }

    @Test
    public void createdDequeWithParameterIsNotNull() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(-5);

        Deque<Integer> deque = new Deque<>(list);

        assertNotNull(deque);
    }

    @Test
    public void createdDequeWithParameterContainsProperInformation() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(9);

        Deque<Integer> deque = new Deque<>(list);

        assertTrue(Arrays.equals(deque.toArray(), list.toArray()));
    }

    @Test
    public void pushFrontDoesNotAddNull() {
        deque.pushFront(1);
        deque.pushFront(null);
        deque.pushFront(-1);

        assertArrayEquals(new Integer[]{-1, 1}, deque.toArray());
    }

    @Test
    public void pushBackDoesNotAddNull() {
        deque.pushBack(1);
        deque.pushBack(null);
        deque.pushBack(-1);

        assertArrayEquals(new Integer[]{1, -1}, deque.toArray());
    }

    @Test
    public void dequeCanNotContainNull() {
        deque.pushBack(15);
        deque.pushFront(16);
        deque.pushFront(null);

        assertFalse(deque.contains(null));
    }

    @Test
    public void dequeSizeIs3() {
        deque.pushBack(99);
        deque.pushFront(9);
        deque.pushFront(31);

        assertEquals(3, deque.getSize());
    }

    @Test
    public void createdDequeSizeIs0() {
        assertEquals(0, deque.getSize());
    }

    @Test
    public void pushFrontAddsIntegersToDequeFront() {
        deque.pushFront(1);
        deque.pushFront(9);
        deque.pushFront(-1);

        assertArrayEquals(new Integer[]{-1, 9, 1}, deque.toArray());
    }

    @Test
    public void pushFrontAddsDatesToDequeFront() {
        Deque<Date> deque = new Deque<>();
        deque.pushFront(new Date(750384000));
        deque.pushFront(new Date(1444608000));

        assertArrayEquals(new Date[]{new Date(1444608000), new Date(750384000)}, deque.toArray());
    }

    @Test
    public void pushBackAddsIntegersToDequeBack() {
        deque.pushBack(1);
        deque.pushBack(9);
        deque.pushBack(-1);

        assertArrayEquals(new Integer[]{1, 9, -1}, deque.toArray());
    }

    @Test
    public void pushBackAddsDatesToDequeBack() {
        Deque<Date> deque = new Deque<>();
        deque.pushBack(new Date(750384000));
        deque.pushBack(new Date(1444608000));

        assertArrayEquals(new Date[]{new Date(750384000), new Date(1444608000)}, deque.toArray());
    }

    @Test
    public void popBackReturnsLastItemWhenDequeIsNotEmpty() {
        deque.pushBack(1);
        deque.pushFront(6);
        deque.pushBack(3);

        assertEquals(new Integer(3), deque.popBack());
    }

    @Test
    public void popBackReturnsNullWhenDequeIsEmpty() {
        assertNull(deque.popBack());
    }

    @Test
    public void popFrontReturnsFirstItemWhenDequeIsNotEmpty() {
        deque.pushFront(6);
        deque.pushBack(1);
        deque.pushBack(55);

        assertEquals(new Integer(6), deque.popFront());
    }

    @Test
    public void popFrontReturnsNullWhenDequeIsEmpty() {
        assertNull(deque.popFront());
    }

    @Test
    public void emptyDequeIsReallyEmpty() {
        assertTrue(deque.isEmpty());
    }

    @Test
    public void dequeContainsNumber42() {
        deque.pushBack(4);
        deque.pushFront(2);
        deque.pushFront(42);

        assertTrue(deque.contains(42));
    }

    @Test
    public void dequeDoesNotContainNumber7() {
        deque.pushBack(4);
        deque.pushFront(8);

        assertFalse(deque.contains(7));
    }

    @Test
    public void dequeIsEmptyAfterClear() {
        deque.pushBack(23);
        deque.pushFront(42);

        deque.clear();

        assertTrue(deque.isEmpty());
    }
}
