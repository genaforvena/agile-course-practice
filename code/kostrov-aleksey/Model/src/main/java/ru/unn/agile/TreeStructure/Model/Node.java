package ru.unn.agile.TreeStructure.Model;


public class Node<TypeKey extends Comparable<TypeKey>, TypeData> {
    private TypeData data;
    private final TypeKey key;
    private Node<TypeKey, TypeData> leftChild, rightChild, parent;

    public Node(final TypeKey key, final TypeData data) {
        leftChild = null;
        rightChild = null;
        parent = null;
        this.key = key;
        this.data = data;
    }

    public TypeKey getKey() {
        return key;
    }

    public TypeData getData() {
        return this.data;
    }

    public void setData(final TypeData data) {
        this.data = data;
    }

    public void setParent(final Node<TypeKey, TypeData> parent) {
        this.parent = parent;
    }

    public Node<TypeKey, TypeData> getParent() {
        return parent;
    }

    public void setLeftChild(final Node<TypeKey, TypeData> node) {
       leftChild = node;
    }

    public Node<TypeKey, TypeData> getLeftChild() {
        return leftChild;
    }

    public void setRightChild(final Node<TypeKey, TypeData> node) {
        rightChild = node;
    }

    public Node<TypeKey, TypeData> getRightChild() {
        return rightChild;
    }
}
