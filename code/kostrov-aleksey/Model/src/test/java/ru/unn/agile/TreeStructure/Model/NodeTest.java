package ru.unn.agile.TreeStructure.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {
    private Node<Integer, String> nodeWithDataString, nodeChild;
    private Node<Integer, Integer> nodeWithDataNumber;

    @Before
    public void initializeNode() {
        nodeWithDataString = new Node<>(1, "initialString");
    }

    @Test
    public void canCreateNode() {
        assertNotNull(nodeWithDataString);
    }

    @Test
    public void canGetKey() {
        Integer oneKey = nodeWithDataString.getKey();
        assertEquals(Integer.valueOf(1), oneKey);
    }

    @Test
    public void canSetString() {
        nodeWithDataString.setData("answer");
        assertEquals("answer", nodeWithDataString.getData());
    }

    @Test
    public void canSetNumber() {
        nodeWithDataNumber = new Node<>(1, 10);
        nodeWithDataNumber.setData(20);
        assertEquals(Integer.valueOf(20), nodeWithDataNumber.getData());
    }

    @Test
    public void canGetNumber() {
        nodeWithDataNumber = new Node<>(10, 10);
        assertEquals(Integer.valueOf(10), nodeWithDataNumber.getData());
    }

    @Test
    public void canGetString() {
        assertEquals("initialString", nodeWithDataString.getData());
    }

    @Test
    public void canSetLeftChild() {
        nodeChild = new Node<>(80, "child");
        nodeWithDataString.setLeftChild(nodeChild);
        assertNotNull(nodeWithDataString.getLeftChild());
    }

    @Test
    public void canGetLeftChild() {
        nodeChild = new Node<>(80, "child");
        nodeWithDataString.setLeftChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getLeftChild());
    }

    @Test
    public void canSetRightChild() {
        nodeChild = new Node<>(80, "child");
        nodeWithDataString.setRightChild(nodeChild);
        assertNotNull(nodeWithDataString.getRightChild());
    }

    @Test
    public void canGetRightChild() {
        nodeChild = new Node<>(80, "child");
        nodeWithDataString.setRightChild(nodeChild);
        assertEquals(nodeChild, nodeWithDataString.getRightChild());
    }

    @Test
    public void canSetParent() {
        nodeChild = new Node<>(80, "child");
        nodeChild.setParent(nodeWithDataString);
        assertNotNull(nodeChild.getParent());
    }

    @Test
    public void canGetParent() {
        nodeChild = new Node<>(80, "child");
        nodeChild.setParent(nodeWithDataString);
        assertEquals(nodeWithDataString, nodeChild.getParent());
    }
}
