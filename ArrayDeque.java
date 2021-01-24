/**
 * Create an Array Doubly Linked List.
 * @author Katie Huang on 09/09/2020
 */
public class ArrayDeque<T> {
    /** Create the array named items. */
    private T[] items;
    /** Size. */
    private int size;
    /** The first index element of array. */
    private int nextFirst;
    /** The last index element of array. */
    private int nextLast;
    /** The Ratio Factor. */
    private static final int RFACTOR = 2;
    /** The Usage Ratio. */
    private static final double USAGE_RATIO = 0.25;

    /** Create an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**
     * Computed the index immediately “before” a given index.
     * inspired by the tips below the proj1a instructions.
     * @param index int
     * @return the index
     */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /**
     * Computed the index immediately “before” a given index.
     * inspired by the tips below the proj1a instructions.
     * @param index int
     * @return the index
     */
    private int addOne(int index) {
        return (index + 1) % items.length;
    }

    /**
     * Resizes the array for memory storge.
     * @param capacity int
     */
    private void resize(int capacity) {
        T[] resizedArray = (T[]) new Object[capacity];
        int current = addOne(nextFirst);
        for (int i = 0; i < size; i += 1) {
            resizedArray[i] = items[current];
            current = addOne(current);
        }
        items = resizedArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /**
     * Add an item in front of the list.
     * @param item T
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    /**
     * Add an item at the end of the list.
     * @param item T
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextLast] = item;
        size += 1;
        nextLast = addOne(nextLast);
    }

    /**
     * Return true if deque is an empty list.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Print the List.
     */
    public void printDeque() {
        int i = addOne(nextFirst);

        while (i != nextLast) {
            System.out.print(items[i] + " ");
            i = addOne(i);
        }
        System.out.println();
    }

    /**
     * Remove the first item of the list.
     * @return the list that is removed the first item
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int firstIndex = addOne(nextFirst);
        T removed = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size -= 1;

        if ((size < items.length * USAGE_RATIO) && (items.length >= 16)) {
            resize(items.length / 2);
        }
        return removed;
    }

    /**
     * Remove the last item of the list.
     * @return the list without the removed item
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int lastIndex = minusOne(nextLast);
        T removed = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size -= 1;

        if ((size < items.length * USAGE_RATIO) && (size >= 16)) {
            resize(items.length / 2);
        }
        return removed;
    }


    /**
     * Get the ith item at the given index.
     * @param index int
     * @return the output in the index
     */
    public T get(int index) {
        if (index > size) {
            return null;
        }

        return items[(addOne(nextFirst) + index) % items.length];
    }

}

