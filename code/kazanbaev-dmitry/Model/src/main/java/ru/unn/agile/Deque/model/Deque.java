package ru.unn.agile.Deque.model;

import java.util.LinkedList;
import java.util.List;

public class Deque<T> {
    private final List<T> list;

    public Deque() {
        this.list = new LinkedList<>();
    }

    public Deque(final List<T> list) {
        this.list = new LinkedList<>(list);
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) (list.toArray());
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void pushFront(final T item) {
        if (item == null) {
            return;
        }
        list.add(0, item);
    }

    public void pushBack(final T item) {
        if (item == null) {
            return;
        }
        list.add(item);
    }

    public T popBack() {
        if (!isEmpty()) {
            T item = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return item;
        }
        return null;
    }

    public T popFront() {
        if (!isEmpty()) {
            T item = list.get(0);
            list.remove(0);
            return item;
        }
        return null;
    }

    public boolean contains(final T item) {
        return list.contains(item);
    }

    public void clear() {
        list.clear();
    }

    public int getSize() {
        return list.size();
    }
}
