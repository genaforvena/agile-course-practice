package ru.unn.agile.MergeSort.Model;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Enclosed.class)
public class WhenSortingPrimitiveDataTypesArray {
    @RunWith(Parameterized.class)
    public static class MergeSortTestWithSmallArrays {
        public MergeSortTestWithSmallArrays(final Integer[] correctArray,
                                            final Integer[] sortingArray) {
            this.correctArray = correctArray;
            this.sortingArray = sortingArray;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testSmallArrays() {
            return Arrays.asList(new Object[][]{
                {null, null},
                {new Integer[]{}, new Integer[]{}},
                {new Integer[]{1}, new Integer[]{1}},
                {new Integer[]{1, 5}, new Integer[]{5, 1}},
                {new Integer[]{1, 2, 3, 5, 8}, new Integer[]{2, 1, 5, 8, 3}},
                {new Integer[]{1, 1, 3, 5, 5}, new Integer[]{5, 5, 1, 1, 3}},
                {new Integer[]{-6, -3, -1, 0, 6}, new Integer[]{-1, -6, -3, 0, 6}},
            });
        }

        @Test
        public void canSortSmallArrays() {
            MergeSort.sort(sortingArray);

            assertArrayEquals(correctArray, sortingArray);
        }

        private Integer[] correctArray;
        private Integer[] sortingArray;
    }

    @RunWith(Parameterized.class)
    public static class MergeSortRangeTestWithSmallArrays {
        public MergeSortRangeTestWithSmallArrays(final Double[] correctArray,
                                                 final Double[] sortingArray,
                                                 final int fromIndex, final int toIndex) {
            this.correctArray = correctArray;
            this.sortingArray = sortingArray;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testSmallArraysWithRange() {
            return Arrays.asList(new Object[][]{
                {null,
                 null, 0, 0},
                {new Double[]{},
                 new Double[]{}, 0, 0},
                {new Double[]{1.1, 4.9, 5.0, 8.6, 9.0},
                 new Double[]{8.6, 9.0, 1.1, 5.0, 4.9}, 0, 4},
                {new Double[]{8.6, 1.1, 5.0, 9.0, 4.9},
                 new Double[]{8.6, 9.0, 1.1, 5.0, 4.9}, 1, 3},
                {new Double[]{8.6, 9.0, 1.1, 5.0, 4.9},
                 new Double[]{8.6, 9.0, 1.1, 5.0, 4.9}, -1, 1},
                {new Double[]{5.3, 7.2, 4.4, 6.0, 4.1},
                 new Double[]{5.3, 7.2, 4.4, 6.0, 4.1}, -4, -2},
                {new Double[]{2.4, 1.9, 1.9, 6.2, 4.3},
                 new Double[]{2.4, 1.9, 1.9, 6.2, 4.3}, 3, 8},
            });
        }

        @Test
        public void canSortSmallArraysWithRange() {
            MergeSort.sort(sortingArray, fromIndex, toIndex);

            assertArrayEquals(correctArray, sortingArray);
        }

        private Double[] correctArray;
        private Double[] sortingArray;
        private int fromIndex;
        private int toIndex;
    }

    @RunWith(Parameterized.class)
    public static class MergeSortTestWithBigRandomArrays {
        public MergeSortTestWithBigRandomArrays(final int arraySize,
                                                final int maxElementValue,
                                                final long seed) {
            this.arraySize = arraySize;
            this.maxElementValue = maxElementValue;
            this.seed = seed;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> testBigRandomArrays() {
            return Arrays.asList(new Object[][]{
                    {1000, 20, 34516},
                    {10000, 20000, 86724},
                    {20000, 2000000, 49246},
            });
        }

        @Test
        public void canSortBigRandomArrays() {
            Long[] sortingArray = new Long[arraySize];
            Long[] correctArray = new Long[arraySize];
            Random randomGenerator = new Random(seed);
            Long randomValue;
            for (int i = 0; i < arraySize; i++) {
                randomValue = randomGenerator.nextLong() % maxElementValue;
                correctArray[i] = randomValue;
                sortingArray[i] = randomValue;
            }

            Arrays.sort(correctArray);
            MergeSort.sort(sortingArray);

            assertArrayEquals(correctArray, sortingArray);
        }

        private int arraySize;
        private int maxElementValue;
        private long seed;
    }
}

