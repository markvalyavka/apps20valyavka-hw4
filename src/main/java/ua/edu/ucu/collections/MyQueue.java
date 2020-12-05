package ua.edu.ucu.collections;

import java.util.Arrays;

public class MyQueue {
    private ImmutableLinkedList underlyingArray;

    public MyQueue() {
        underlyingArray = new ImmutableLinkedList(new Object[0]);
    }

    public MyQueue(Object[] arr) {
        underlyingArray = new ImmutableLinkedList(arr);
    }

    public void enqueue(Object e) {
        underlyingArray = underlyingArray.addLast(e);
        System.out.println(underlyingArray);
    }

    public Object dequeue() {
        Object dequeued = underlyingArray.getFirst();
        underlyingArray = underlyingArray.removeFirst();
        return dequeued;
    }

    public Object peek() {
        return underlyingArray.getFirst();
    }

    @Override
    public String toString() {
        return "Queue(" + Arrays.toString(underlyingArray.toArray()) + ")";
    }
}
