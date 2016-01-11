package ru.unn.agile.TreeStructure.Model;

public class Tree<TypeKey extends Comparable<TypeKey>, TypeData> {
    private Node<TypeKey, TypeData> root;
    static final int FIRST_VALUE_MORE = 1;
    static final int FIRST_VALUE_LESS = -1;
    static final int VALUES_ARE_EQUAL = 0;

    public Tree() {
        root = null;
    }

    public Tree(final TypeKey key, final TypeData data) {
        root = new Node<>(key, data);
    }

    public boolean addNode(final TypeKey key, final TypeData data) {
        if (root == null) {
            root = new Node<>(key, data);
            return true;
        }
        Node<TypeKey, TypeData> newNode = new Node<>(key, data);
        return add(root, newNode);
    }

    private boolean add(final Node<TypeKey, TypeData> currentNode,
                        final Node<TypeKey, TypeData> newNode) {

        if (currentNode.getKey().compareTo(newNode.getKey()) == FIRST_VALUE_MORE) {
            if (currentNode.getLeftChild() == null) {
                currentNode.setLeftChild(newNode);
                newNode.setParent(currentNode);
                return true;
            }
            return add(currentNode.getLeftChild(), newNode);
        }
        if (currentNode.getKey().compareTo(newNode.getKey()) == FIRST_VALUE_LESS) {
            if (currentNode.getRightChild() == null) {
                currentNode.setRightChild(newNode);
                newNode.setParent(currentNode);
                return true;
            }
            return add(currentNode.getRightChild(), newNode);
        }
        return false;
    }

    public Node<TypeKey, TypeData> searchByKey(final TypeKey key) {
        return search(root, key);
    }

    private Node<TypeKey, TypeData> search(final Node<TypeKey, TypeData> node,
                                                    final TypeKey key) {
        if (node == null) {
            return null;
        }
        if (node.getKey().compareTo(key) == VALUES_ARE_EQUAL) {
            return node;
        }
        if (node.getKey().compareTo(key) == FIRST_VALUE_MORE) {
            return search(node.getLeftChild(), key);
        } else {
            return search(node.getRightChild(), key);
        }
    }

    public boolean truncateByKey(final TypeKey key) {
        Node<TypeKey, TypeData> foundNode = searchByKey(key);

        if (foundNode == null) {
            throw new NullPointerException();
        }
        if (!root.equals(foundNode)) {
            if (foundNode.getParent().getRightChild().getKey().compareTo(key) == VALUES_ARE_EQUAL) {
                foundNode.getParent().setRightChild(null);
            } else {
                foundNode.getParent().setLeftChild(null);
            }
        }
        deleteNode(foundNode);
        if (root.equals(foundNode)) {
            root = null;
        }
        return true;
    }

    private void deleteNode(final Node<TypeKey, TypeData> node) {
        if (node != null) {
            deleteNode(node.getLeftChild());
            deleteNode(node.getRightChild());
            node.setLeftChild(null);
            node.setRightChild(null);
            node.setParent(null);
        }
    }
}
