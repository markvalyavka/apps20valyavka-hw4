package ua.edu.ucu.collections;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int size;

    public ImmutableLinkedList(Object[] arr) {

        // Array to doubly linked list
        size = arr.length;
        if (arr.length == 0) {
            tail = null;
            head = null;
        } else if (arr.length == 1) {
            head = new Node(arr[0]);
            head.next = null;
            head.prev = null;
        } else {
            head = new Node(arr[0]);
            head.prev = null;
            tail = new Node(arr[arr.length - 1]);
            tail.next = null;
            Node prev = head;
            for (int i = 1; i < size - 1; i++) {
                Node newNode = new Node(arr[i]);
                newNode.prev = prev;
                prev.next = newNode;
                prev = prev.next;
            }
            prev.next = tail;
            tail.prev = prev;
        }
    }

    public void checkBounds(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(size, new Object[]{e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        checkBounds(index);
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (isEmpty()) {
            return new ImmutableLinkedList(c);
        } else {
            checkBounds(index);
            Object[] currArray = toArray();
            Object[] newArray = new Object[size + c.length];
            System.arraycopy(currArray, 0, newArray, 0, index);
            System.arraycopy(c, 0, newArray, index, c.length);
            System.arraycopy(currArray, index, newArray, index + c.length, size - index);
            return new ImmutableLinkedList(newArray);
        }
    }

    @Override
    public Object get(int index) {
        checkBounds(index);
        Object[] currArray = toArray();
        return currArray[index];
    }

    @Override
    public ImmutableList remove(int index) {
        checkBounds(index);
        Object[] currArray = toArray();
        Object[] newArray = new Object[size - 1];
        System.arraycopy(currArray, 0, newArray, 0, index);
        System.arraycopy(currArray, index + 1, newArray, index, size - index - 1);
        return new ImmutableLinkedList(newArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkBounds(index);
        Object[] currArray = toArray();
        Object[] newArray = new Object[size];
        System.arraycopy(currArray, 0, newArray, 0, size);
        newArray[index] = e;
        return new ImmutableLinkedList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        Object[] currArray = toArray();
        for (int i = 0; i < size; i++) {
            if (currArray[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        Object[] newArray = new Object[size];
        Arrays.fill(newArray, null);
        return new ImmutableLinkedList(newArray);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node curr = head;
        int index = 0;
        while (curr != null) {
            array[index] = curr.item;
            curr = curr.next;
            index += 1;
        }
        System.out.println(Arrays.toString(Arrays.stream(array).toArray()));
        return array;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(size, e);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(size - 1);
    }

    public Object getFirst() {
        return head.item;
    }

    public Object getLast() {
        return tail.item;
    }

    @Override
    public String toString() {
        return "ImmutableLinkedList(" + Arrays.toString(Arrays.stream(toArray()).toArray()) + ")";
    }
}
