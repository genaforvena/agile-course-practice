package ru.unn.agile.MergeSort.Model;

import java.util.Vector;

public final class MergeSort {
    public static <Type extends Comparable<Type>> void sort(final Type[] sortingArray) {
        if (sortingArray == null) {
            return;
        }

        mergeSort(sortingArray, 0, sortingArray.length - 1);
    }

    public static <Type extends Comparable<Type>> void sort(final Type[] sortingArray,
                                                            final int fromIndex,
                                                            final int toIndex) {
        if (sortingArray == null) {
            return;
        }

        if (isSortingRangeCorrect(fromIndex, toIndex, sortingArray.length)) {
            mergeSort(sortingArray, fromIndex, toIndex);
        }
    }

    private static boolean isSortingRangeCorrect(final int fromIndex, final int toIndex,
                                                 final int arrayLength) {
        return fromIndex < toIndex && fromIndex >= 0 && toIndex < arrayLength;
    }

    private static boolean isSortingRangeBiggerOneElement(final int fromIndex, final int toIndex) {
        return  fromIndex < toIndex;
    }

    private static <Type extends Comparable<Type>> void mergeSort(final Type[] sortingArray,
                                                                  final int fromIndex,
                                                                  final int toIndex) {
        if (isSortingRangeBiggerOneElement(fromIndex, toIndex)) {
            int middleIndex = fromIndex + (toIndex - fromIndex) / 2;

            mergeSort(sortingArray, fromIndex, middleIndex);
            mergeSort(sortingArray, middleIndex + 1, toIndex);

            merge(sortingArray, fromIndex, middleIndex + 1, toIndex);
        }
    }

    private static <Type extends Comparable<Type>> void merge(final Type[] sortingArray,
                                                              final int leftStartIndex,
                                                              final int rightStartIndex,
                                                              final int rightEndIndex) {
        int leftEndIndex = rightStartIndex - 1;
        int currentLeftPos = leftStartIndex;
        int currentRightPos = rightStartIndex;

        Vector<Type> bufferArray = new Vector<Type>(sortingArray.length);

        while (currentLeftPos <= leftEndIndex && currentRightPos <= rightEndIndex) {
            if (sortingArray[currentLeftPos].compareTo(sortingArray[currentRightPos]) <= 0) {
                bufferArray.add(sortingArray[currentLeftPos++]);
            } else {
                bufferArray.add(sortingArray[currentRightPos++]);
            }
        }

        while (currentLeftPos <= leftEndIndex) {
            bufferArray.add(sortingArray[currentLeftPos++]);
        }

        while (currentRightPos <= rightEndIndex) {
            bufferArray.add(sortingArray[currentRightPos++]);
        }

        int bufferPosition = 0;
        for (int i = leftStartIndex; i <= rightEndIndex; i++) {
            sortingArray[i] = bufferArray.get(bufferPosition++);
        }
    }

    private MergeSort() {
    }
}
