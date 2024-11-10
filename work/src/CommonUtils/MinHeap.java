package CommonUtils;

import CommonUtils.Interfaces.MinHeapInterface;

import java.awt.*;
import java.util.Arrays;

/**
 * Implements our MinHeapInterface and adds a constructor
 * <p>
 * <b>251 students: You are explicitly forbidden from using java.util.Queue (including any subclass
 *   like PriorityQueue) and any other java.util.* library EXCEPT java.util.Arrays and java.util.Vector.
 *   Write your own implementation of a MinHeap.</b>
 *
 * @param <E> the type of object this heap will be holding
 */
public class MinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {

    private final int INIT_CAPACITY = 10;
    private E[] heap;
    private int size;

    /**
     * Constructs an empty min heap
     */
    @SuppressWarnings("unchecked")
    public MinHeap(){

        this.heap = (E[]) new Comparable[INIT_CAPACITY];
        this.size = 0;
    }

    /**
     * Resize the heap when it becomes full
     */
    private void resize() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    /**
     * Swap two elements in the heap array
     *
     * @param i index of first element
     * @param j index of second element
     */
    private void swap(int i, int j) {
        E temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Restore heap property after insertion by bubbling up
     *
     * @param index index of the newly added element
     */
    private void bubbleUp(int index) {

        int parent = (index - 1) / 2;

        while (index > 0 && heap[index].compareTo(heap[parent]) < 0) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    /**
     * A recursive method to heapify (sort the root to where it should go) a
     *   subtree with the root at given index
     * Assumes the subtrees are already heapified.
     * (The purpose of this method is to balance tree starting at the root)
     * @param i root of the subtree to heapify
     */
    private void heapify(int i) {

        int smallest = i;

        int left = 2 * i + 1;;
        int right = 2 * i + 2;

        // Find the smallest among root, left child, and right child
        if (left < size && heap[left].compareTo(heap[smallest]) < 0) {
            smallest = left;
        }
        if (right < size && heap[right].compareTo(heap[smallest]) < 0) {
            smallest = right;
        }

        // If the smallest is not the root, swap and continue heapifying
        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }
    }

    /**
     * Adds the item to the min heap
     *
     * @param item item to add
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public void add(E item) {

        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }

        if (size == heap.length) {
            resize();
        }

        heap[size++] = item;
        bubbleUp(size - 1); // ensure the heap property is maintained
    }

    /**
     * Empties the heap.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        heap = (E[]) new Comparable[INIT_CAPACITY];
        size = 0;
    }

    /**
     * Returns the minimum element without removing it, or returns <code>null</code> if heap is empty
     *
     * @return the minimum element in the heap, or <code>null</code> if heap is empty
     */
    @Override
    public E peekMin() {

        if (size == 0) {
            return null;
        }

        return heap[0]; // Min heap always has minimum value as root (index 0)
    }

    /**
     * Remove and return the minimum element in the heap, or returns <code>null</code> if heap is empty
     *
     * @return the minimum element in the heap, or <code>null</code> if heap is empty
     */
    @Override
    public E removeMin() {

        if (size == 0) {
            return null;
        }

        E min = heap[0]; // Preserve min value.

        heap[0] = heap[--size]; // Move the last element to the root

        heapify(0); // restore the heap property by heapifying down
        return min;
    }

    /**
     * Returns the number of elements in the heap
     *
     * @return integer representing the number of elements in the heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
     *
     * @param g graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        //DO NOT MODIFY NOR IMPLEMENT THIS FUNCTION
        if(g != null) g.getColor();
        //todo GRAPHICS DEVELOPER:: draw the MinHeap how we discussed
        //251 STUDENTS:: YOU ARE NOT THE GRAPHICS DEVELOPER!
    }
}
