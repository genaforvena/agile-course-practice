package ru.unn.agile.LeftistHeap.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class LeftistHeapTest {
    @RunWith(Parameterized.class)
    public static class WhenMergingTwoHeaps {
        public WhenMergingTwoHeaps(final int firstHeapSize,
                                   final int secondHeapSize,
                                   final int resultHeapSize) {
            this.firstHeapSize = firstHeapSize;
            this.secondHeapSize = secondHeapSize;
            this.resultHeapSize = resultHeapSize;
        }

        @Before
        public void initializeHeaps() {
            firstHeap = new LeftistHeap<>();
            secondHeap = new LeftistHeap<>();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizes() {
            return Arrays.asList(new Object[][]{
                    {0, 0, 0},
                    {0, 1, 1},
                    {1, 0, 1},
                    {1, 1, 2},
                    {3, 5, 8},
                    {250, 260, 510}
            });
        }

        @Test
        public void canMergeTwoHeaps() {
            setUpHeap(firstHeap, firstHeapSize);
            setUpHeap(secondHeap, secondHeapSize);

            firstHeap.merge(secondHeap);

            assertEquals(resultHeapSize, firstHeap.getSize());
        }

        private int firstHeapSize;
        private int secondHeapSize;
        private int resultHeapSize;
        private LeftistHeap<Integer> firstHeap;
        private LeftistHeap<Integer> secondHeap;
    }

    @RunWith(Parameterized.class)
    public static class WhenInsertElementToHeap {
        public WhenInsertElementToHeap(final int startHeapSize, final int resultHeapSize) {
            this.startHeapSize = startHeapSize;
            this.resultHeapSize = resultHeapSize;
        }

        @Before
        public void initializeHeap() {
            heap = new LeftistHeap<>();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizes() {
            return Arrays.asList(new Object[][]{
                    {0, 1},
                    {1, 2},
                    {10, 11},
                    {150, 151}
            });
        }

        @Test
        public void canMergeTwoHeaps() {
            setUpHeap(heap, startHeapSize);

            heap.insert(heapElementToInsert);

            assertEquals(resultHeapSize, heap.getSize());
        }

        private final Integer heapElementToInsert = 10;
        private int startHeapSize;
        private int resultHeapSize;
        private LeftistHeap<Integer> heap;
    }

    @RunWith(Parameterized.class)
    public static class WhenDecreasingKeyOfElement {
        public WhenDecreasingKeyOfElement(final int heapSize, final Integer targetKey) {
            this.heapSize = heapSize;
            this.targetKey = targetKey;
        }

        @Before
        public void initializeHeap() {
            heap = new LeftistHeap<>();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizesAndKeys() {
            return Arrays.asList(new Object[][]{
                    {1, -1},
                    {10, -5},
                    {100, 0},
                    {150, 0}
            });
        }

        @Test
        public void canDecreaseKeyOfRootElement() {
            setUpHeap(heap, heapSize);

            heap.decreaseKey(heap.getRoot(), targetKey);

            assertEquals(targetKey, heap.getRoot().getElement());
        }

        @Test
        public void canDecreaseKeyOfNotRootElement() {
            setUpHeap(heap, heapSize);
            LeftistHeapNode<Integer> child = heap.findNodeByKey(heapSize / 2);

            heap.decreaseKey(child, targetKey);

            assertEquals(targetKey, heap.getRoot().getElement());
        }

        private int heapSize;
        private Integer targetKey;
        private LeftistHeap<Integer> heap;
    }

    @RunWith(Parameterized.class)
    public static class WhenDeleteRootElementFromHeap {
        public WhenDeleteRootElementFromHeap(final int elementsInHeap,
                                             final Integer returnValue) {
            this.elementsInHeap = elementsInHeap;
            this.returnValue = returnValue;
        }

        @Before
        public void initializeHeap() {
            heap = new LeftistHeap<>();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizesAndKeys() {
            return Arrays.asList(new Object[][]{
                    {0, null},
                    {1, 0},
                    {5, 0},
                    {150, 0}
            });
        }

        @Test
        public void canDeleteRootElementFromHeap() {
            setUpHeap(heap, elementsInHeap);

            Integer deleteResult = heap.extractMin();

            assertEquals(returnValue, deleteResult);
        }

        private int elementsInHeap;
        private Integer returnValue;
        private LeftistHeap<Integer> heap;
    }

    @RunWith(Parameterized.class)
    public static class WhenFindAndDeleteElementFromHeap {
        public WhenFindAndDeleteElementFromHeap(final int elementsInHeap,
                                                final Integer keyToDelete,
                                                final Integer[] correctValues) {
            this.elementsInHeap = elementsInHeap;
            this.keyToDelete = keyToDelete;
            this.correctValues = correctValues;
        }

        @Before
        public void initializeHeap() {
            heap = new LeftistHeap<>();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getHeapSizesAndKeys() {
            return Arrays.asList(new Object[][]{
                    {2, 1, new Integer[] {0}},
                    {5, 4, new Integer[] {0, 1, 2, 3}},
                    {5, 1, new Integer[] {0, 2, 3, 4}},
                    {5, 2, new Integer[] {0, 1, 3, 4}}
            });
        }

        @Test
        public void canFindAndDeleteElementFromHeap() {
            setUpHeap(heap, elementsInHeap);

            LeftistHeapNode<Integer> foundNode = heap.findNodeByKey(keyToDelete);
            heap.delete(foundNode);

            assertArrayEquals(correctValues, heap.toSortedArray());
        }

        private int elementsInHeap;
        private Integer keyToDelete;
        private Integer[] correctValues;
        private LeftistHeap<Integer> heap;
    }

    public static class LeftistHeapSimpleTests {
        @Before
        public void initializeHeap() {
            heap = new LeftistHeap<>();
        }

        @Test
        public void canCreateEmptyLeftistHeap() {
            setUpHeap(heap, 0);

            assertTrue(heap.isEmpty());
        }

        @Test
        public void heapWorkProperlyWithDates() {
            LeftistHeap<Date> dateHeap = new LeftistHeap<>();
            Date[] dateArray = {new Date(1245624241), new Date(2132524677)};

            dateHeap.insert(dateArray[0]);
            dateHeap.insert(dateArray[1]);

            assertArrayEquals(dateArray, dateHeap.toSortedArray());
        }

        @Test
        public void createdHeapContainProperInformation() {
            setUpHeap(heap, 5);
            Integer[] correctValues = {0, 1, 2, 3, 4};

            assertArrayEquals(correctValues, heap.toSortedArray());
        }

        @Test
        public void canClearNotEmptyHeap() {
            setUpHeap(heap, 5);

            heap.clear();

            assertTrue(heap.isEmpty());
        }

        @Test
        public void isParentFieldInNodeSetsCorrect() {
            setUpHeap(heap, 10);

            LeftistHeapNode<Integer> child = heap.getRoot().getLeftChild();

            assertEquals(child.getParent(), heap.getRoot());
        }

        @Test
        public void canClearHeapByDeletingSingleElements() {
            setUpHeap(heap, 50);

            for (Integer keyToFind = heap.getSize() - 1; keyToFind >= 0; keyToFind--) {
                LeftistHeapNode<Integer> foundNode = heap.findNodeByKey(keyToFind);
                heap.delete(foundNode);
            }

            assertEquals(0, heap.getSize());
        }

        @Test(expected = NullPointerException.class)
        public void tryingToFindNotExistingElementThrowsException() {
            setUpHeap(heap, 20);
            Integer keyToFind = 21;

            heap.findNodeByKey(keyToFind);
        }

        @Test
        public void canDeleteNotRootElementFromNotEmptyHeap() {
            int elementsInHeap = 5;
            setUpHeap(heap, elementsInHeap);

            heap.delete(heap.getRoot().getRightChild());

            assertEquals(elementsInHeap - 1, heap.getSize());
        }

        @Test
        public void deepCopyOfHeapHaveDifferentAddress() {
            setUpHeap(heap, 5);

            LeftistHeap<Integer> deepCopy = new LeftistHeap<>(heap);

            assertTrue(heap != deepCopy);
        }

        @Test
        public void canChangeDeepCopyWithoutChangingSourceHeap() {
            setUpHeap(heap, 5);
            Integer[] correctValues = {0, 1, 2, 3, 4};

            LeftistHeap<Integer> deepCopy = new LeftistHeap<>(heap);
            multiplyAllKeysInHeapByTen(deepCopy.getRoot(), null);

            assertArrayEquals(correctValues, heap.toSortedArray());
        }

        @Test
        public void canGetSortedArrayWithoutHeapClearing() {
            setUpHeap(heap, 5);

            heap.toSortedArray();

            assertEquals(5, heap.getSize());
        }

        @Test
        public void canGetProperSortedArrayWithoutHeapClearing() {
            setUpHeap(heap, 5);
            Integer[] correctValues = {0, 1, 2, 3, 4};

            assertArrayEquals(correctValues, heap.toSortedArray());
        }

        @Test(expected = NullPointerException.class)
        public void canNotDeleteNullElement() {
            setUpHeap(heap, 5);

            heap.delete(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void canNotChangeKeyToLargerOne() {
            setUpHeap(heap, 5);

            heap.decreaseKey(heap.getRoot(), 10);
        }

        @Test
        public void mergeHeapWithItselfDoNothing() {
            setUpHeap(heap, 5);
            Integer[] correctValues = {0, 1, 2, 3, 4};

            heap.merge(heap);

            assertArrayEquals(correctValues, heap.toSortedArray());
        }

        private void multiplyAllKeysInHeapByTen(final LeftistHeapNode<Integer> leftNode,
                                                final LeftistHeapNode<Integer> rightNode) {
            if (leftNode != null) {
                leftNode.setElement(10 * leftNode.getElement());
                multiplyAllKeysInHeapByTen(leftNode.getLeftChild(), leftNode.getRightChild());
            }

            if (rightNode != null) {
                rightNode.setElement(10 * rightNode.getElement());
                multiplyAllKeysInHeapByTen(rightNode.getLeftChild(), rightNode.getRightChild());
            }
        }
        private LeftistHeap<Integer> heap;
    }

    private static void setUpHeap(final LeftistHeap<Integer> heap, final int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            heap.insert(i);
        }
    }
}
