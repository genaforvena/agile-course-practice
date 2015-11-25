package ru.unn.agile.Stack.Model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StackTest {
    @Test
    public void initiatingStackFromIntArray() {
        Integer[] inputArray = {1, 2, 3};
        Stack myStack = new Stack(inputArray);
        assertEquals(Arrays.asList(1, 2, 3), myStack.toArrayList());
    }

    @Test
     public void initiatingStackFromStringArray() {
        String[] inputArray = {"one", "two", "three"};
        Stack myStack = new Stack(inputArray);
        assertEquals(Arrays.asList("one", "two", "three"), myStack.toArrayList());
    }

    @Test
    public void initiatingEmptyStack() {
        Stack myStack = new Stack();
        assertEquals(Arrays.asList(), myStack.toArrayList());
    }

    @Test
    public void pushElementToEmptyStack() {
        Stack myStack = new Stack();
        Integer element = 1;
        myStack.push(element);
        assertEquals(Arrays.asList(1), myStack.toArrayList());
    }

    @Test
    public void pushTenIntegerElementsToEmptyStack() {
        Stack myStack = new Stack();
        Integer[] elements = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < elements.length; ++i) {
            myStack.push(elements[i]);
        }

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), myStack.toArrayList());
    }

    @Test
    public void pushTenStringElementsToEmptyStack() {
        Stack myStack = new Stack();
        String[] elements = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        for (int i = 0; i < elements.length; ++i) {
            myStack.push(elements[i]);
        }

        assertEquals(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
                myStack.toArrayList());
    }

    @Test
    public void popReturnPeakFrom123Stack() {
        Integer[] inputArray = {1, 2, 3};
        Stack myStack = new Stack(inputArray);

        assertEquals(3, myStack.pop());
    }

    @Test
    public void popRemovesPeakFrom123Stack() {
        Integer[] inputArray = {1, 2, 3};
        Stack myStack = new Stack(inputArray);
        myStack.pop();

        assertEquals(Arrays.asList(1, 2), myStack.toArrayList());
    }

    @Test
    public void popReturnPeakFromStringStack() {
        String[] inputArray = {"one", "two", "three"};
        Stack myStack = new Stack(inputArray);

        assertEquals("three", myStack.pop());
    }

    @Test
    public void popRemovesPeakFromEmptyStack() {
        Stack myStack = new Stack();
        myStack.pop();

        assertEquals(Arrays.asList(), myStack.toArrayList());
    }

    @Test
    public void popReturnPeakFromEmptyStack() {
        Stack myStack = new Stack();

        assertEquals(null, myStack.pop());
    }

    @Test
    public void pushAndPopReturnPeakToEmptyStack() {
        Stack myStack = new Stack();
        myStack.push(10);

        assertEquals(10, myStack.pop());
    }

    @Test
    public void popAndPushToEmptyStack() {
        Stack myStack = new Stack();
        myStack.pop();
        myStack.push(11);

        assertEquals(Arrays.asList(11), myStack.toArrayList());
    }

    @Test
    public void peakFromStackWithFewElements() {
        Stack myStack = new Stack(new Integer[]{1, 2, 3});

        assertEquals(3, myStack.peak());
    }

    @Test
    public void peakFromEmptyStack() {
        Stack myStack = new Stack();

        assertEquals(null, myStack.peak());
    }

    @Test
    public void checkIsPeakNotChangeSteck() {
        Stack myStack = new Stack(new Integer[]{1, 2, 3});

        assertEquals(Arrays.asList(1, 2, 3), myStack.toArrayList());
    }
}
