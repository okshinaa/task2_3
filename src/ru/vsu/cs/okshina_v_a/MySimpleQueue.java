package ru.vsu.cs.okshina_v_a;

import java.util.NoSuchElementException;

public class MySimpleQueue<E> {
    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<E> first = null;
    private Node<E> last = null;

    public MySimpleQueue() {
    }

    public MySimpleQueue(E[] arr) {
        for (E element : arr)
            addLast(element);
    }

    public void addFirst(E element) {
        Node<E> oldFirst = first;
        Node<E> newNode = new Node<>(element, oldFirst);
        first = newNode;

        if (oldFirst == null)
            last = newNode;

        size++;
    }

    public static MySimpleQueue<Integer> arrayToMyQueue(int[] arr) {
        MySimpleQueue<Integer> queue = new MySimpleQueue<>();

        for (int i = 0; i < arr.length; i++) {
            queue.addLast(arr[i]);
        }

        return queue;
    }


    public void addLast(E element) {
        Node<E> oldLast = last;
        Node<E> newNode = new Node<>(element, null);
        last = newNode;

        if (oldLast == null)
            first = newNode;
        else
            oldLast.next = newNode;

        size++;
    }

    public void addAll(E[] elements) {
        for (E el : elements)
            addLast(el);
    }

    public E removeFirst() {
        Node<E> oldFirst = first;

        if (oldFirst == null)
            throw new NoSuchElementException();

        first = oldFirst.next;
        oldFirst.next = null;

        size--;

        return oldFirst.element;
    }

    public E removeLast() {
        Node<E> currNode = first;

        if (currNode.next == null) {
            first = last = null;
            size--;
            return currNode.element;
        } else {
            while (currNode.next.next != null)
                currNode = currNode.next;
        }

        Node<E> oldLast = currNode.next;
        currNode.next = null;
        last = currNode;

        size--;

        return oldLast.element;
    }

    public E getFirst() {
        if (first == null)
            throw new NoSuchElementException();

        return first.element;
    }

    public E getLast() {
        if (last == null)
            throw new NoSuchElementException();

        return last.element;
    }

    public E popFirst() {
        return removeFirst();
    }

    public E popLast() {
        return removeLast();
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<E> currElement = first;

        while (currElement != null) {
            str.append(currElement.element).append(" ");
            currElement = currElement.next;
        }

        return str.toString();
    }
}