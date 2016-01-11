package ru.unn.agile.LeftistHeap.model;

public class LeftistHeapNode<Type extends Comparable<Type>> {
    private Type element;
    private LeftistHeapNode<Type> leftChild;
    private LeftistHeapNode<Type> rightChild;
    private LeftistHeapNode<Type> parent;
    private int rank;

    public LeftistHeapNode(final Type element) {
        this(element, null, null, null, 0);
    }

    public void swapChildren() {
        LeftistHeapNode<Type> temp = leftChild;
        leftChild = rightChild;
        rightChild = temp;
    }

    public void clear() {
        element = null;
        leftChild = null;
        rightChild = null;
        parent = null;
        rank = 0;
    }

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }

    public boolean haveChildren() {
        return hasLeftChild() && hasRightChild();
    }

    public boolean isEmpty() {
        return !haveChildren() && element == null && parent == null;
    }

    public boolean isRightChildHasLargerRank() {
        return rightChild.getRank() > leftChild.getRank();
    }
    public int compareTo(final LeftistHeapNode<Type> rhsNode) {
        return this.element.compareTo(rhsNode.element);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(final int rank) {
        this.rank = rank;
    }

    public Type getElement() {
        return element;
    }

    public void setElement(final Type element) {
        this.element = element;
    }

    public LeftistHeapNode<Type> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(final LeftistHeapNode<Type> leftChild) {
        this.leftChild = leftChild;
    }

    public LeftistHeapNode<Type> getRightChild() {
        return rightChild;
    }

    public void setRightChild(final LeftistHeapNode<Type> rightChild) {
        this.rightChild = rightChild;
    }

    public LeftistHeapNode<Type> getParent() {
        return parent;
    }

    public void setParent(final LeftistHeapNode<Type> parent) {
        this.parent = parent;
    }

    private LeftistHeapNode(final Type element,
                            final LeftistHeapNode<Type> leftChild,
                            final LeftistHeapNode<Type> rightChild,
                            final LeftistHeapNode<Type> parent,
                            final int rank) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        this.rank = rank;
    }
}
