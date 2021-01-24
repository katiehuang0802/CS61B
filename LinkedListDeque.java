/**
 * Create a Doubly Linked List.
 *
 * @author Katie Huang on 09/07/2020
 */
public class LinkedListDeque<T> {
    /**
     * Create the sentinel.
     */
    private DequeNode sentinel;
    /**
     * Create the size.
     */
    private int size;

    /** Create the class for item,next,prev. */
    private class DequeNode {
        /**
         * The element prev.
         */
        private DequeNode prev;
        /**
         * The element item.
         */
        private T item;
        /**
         * The element next.
         */
        private DequeNode next;

        /** Create the item, prev, and next.
         * @param p DequeNode
         * @param i T
         * @param n DequeNode
         */
        DequeNode(DequeNode p, T i, DequeNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /** Create an empty list that has prev, item, next. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new DequeNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Add an item in front of the list.
     * @param item T
     */
    public void addFirst(T item) {
        sentinel.next = new DequeNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Add an item at the last of the list.
     * @param item T
     */
    public void addLast(T item) {
        sentinel.prev = new DequeNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** Return ture if the list is empty. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the size. */
    public int size() {
        return size;
    }

    /** Print the list. */
    public void printDeque() {
        DequeNode current = sentinel;
        if (sentinel == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Deque Linkedlist: ");
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    /** Remove the first item of the list.
     * @return the newlist without the first item
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return first;
        }
    }

    /** Remove the last item of the list.
     * @return the new list without the last item
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return last;
        }
    }

    /** Get the ith item from the list.
     * @param index int
     * @return the ith item from the list
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            DequeNode current = sentinel.next;
            while (index > 0) {
                current = current.next;
                index = index - 1;
            }
            return current.item;
        }
    }

    /** Get the ith item from the list using recursion.
     * @param index int
     * @return the ith item from the list
     */
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursivehelperfunc(sentinel.next, index);
    }

    /** Help get the item for the getRecursive.
     * @param index int
     * @param current DequeNode
     * @return the item from the list
     */
    private T getRecursivehelperfunc(DequeNode current, int index) {
        if (index == 0) {
            return current.item;
        }
        return getRecursivehelperfunc(current.next, index - 1);
    }
}

