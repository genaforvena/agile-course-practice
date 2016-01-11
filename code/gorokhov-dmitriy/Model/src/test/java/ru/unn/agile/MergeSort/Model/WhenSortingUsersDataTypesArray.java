package ru.unn.agile.MergeSort.Model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class WhenSortingUsersDataTypesArray {
    public WhenSortingUsersDataTypesArray(final Point2D[] correctArray,
                                          final Point2D[] sortingArray) {
        this.correctArray = correctArray;
        this.sortingArray = sortingArray;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testComplexNumberArrays() {
        return Arrays.asList(new Object[][]{
            {new Point2D[]{new Point2D(0, 0)},
             new Point2D[]{new Point2D(0, 0)}},
            {new Point2D[]{new Point2D(0, 1), new Point2D(4, 3),
                           new Point2D(4, 9), new Point2D(5, 1)},
             new Point2D[]{new Point2D(5, 1), new Point2D(4, 9),
                           new Point2D(4, 3), new Point2D(0, 1)}}
        });
    }

    @Test
    public void canSortComplexNumberArrays() {
        MergeSort.sort(sortingArray);

        assertArrayEquals(correctArray, sortingArray);
    }

    private Point2D[] correctArray;
    private Point2D[] sortingArray;
}

