package ua.edu.ucu.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
    }

    public Object dequeue() {
        Object dequeued = underlyingArray.getFirst();
        underlyingArray = underlyingArray.removeFirst();
        return dequeued;
    }

    public Object peek() {
        return underlyingArray.getFirst();
    }

    public <T> Iterator<T> iterator() {

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !underlyingArray.isEmpty();
            }

            @Override
            public T next() {
                if (underlyingArray.isEmpty()) {
                    throw new NoSuchElementException();
                }

                return (T) dequeue();
            }
        };
    }

    @Override
    public String toString() {
        return "Queue(" + Arrays.toString(underlyingArray.toArray()) + ")";
    }
}
