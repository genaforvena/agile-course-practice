package ru.unn.agile.LeftistHeap.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LeftistHeapNodeTest {
    private LeftistHeapNode<Integer> node;
    private LeftistHeapNode<Integer> leftChild;
    private LeftistHeapNode<Integer> rightChild;

    @Before
    public void setUp() {
        node = new LeftistHeapNode<>(5);
        leftChild = new LeftistHeapNode<>(8);
        rightChild = new LeftistHeapNode<>(9);
        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);
    }

    @Test
    public void canSwapChildren() {
        node.swapChildren();

        assertArrayEquals(new Object[]{leftChild, rightChild},
                new Object[]{node.getRightChild(), node.getLeftChild()});
    }

    @Test
    public void canClearNode() {
        node.clear();

        assertTrue(node.isEmpty());
    }

    @Test
    public void canCheckIfTheNodeIsEmpty() {
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(null);

        assertTrue(node.isEmpty());
    }

    @Test
    public void whenRightChildRankMoreThanLeft() {
        leftChild.setRank(0);
        rightChild.setRank(1);

        assertTrue(node.isRightChildHasLargerRank());
    }

    @Test
    public void canCreateNode() {
        LeftistHeapNode<Integer> node = new LeftistHeapNode<>(5);

        assertNotNull(node);
    }
}
