package com.carlospinan.datastructures.week3;

import java.util.Arrays;

/**
 * @author Carlos Piñan
 */
public class Heap {

    private Integer[] completeBinaryTree;
    private int size;
    private int currentSize;

    public Heap(int size) {
        this.completeBinaryTree = new Integer[size];
        this.currentSize = 0;
        this.size = size;
    }

    private int leftChildIndex(int idx) {
        return 2 * idx + 1;
    }

    private Integer getLeftChild(int idx) {
        int leftChildIndex = leftChildIndex(idx);
        if (isSafe(leftChildIndex)) {
            return completeBinaryTree[idx];
        }
        return null;
    }

    private int rightChildIndex(int idx) {
        return leftChildIndex(idx) + 1;
    }

    private Integer getRightChild(int idx) {
        int rightChildIndex = rightChildIndex(idx);
        if (isSafe(rightChildIndex)) {
            return completeBinaryTree[idx];
        }
        return null;
    }

    private int parentIndex(int idx) {
        return (idx - 1) / 2;
    }

    private Integer getParent(int idx) {
        int parentIndex = parentIndex(idx);
        if (isSafe(parentIndex)) {
            return completeBinaryTree[parentIndex];
        }
        return null;
    }

    private boolean isSafe(int idx) {
        return idx >= 0 && idx < size;
    }

    private boolean hasCapacity() {
        return currentSize < size;
    }

    private Integer getLastLeaf() {
        if (currentSize > 0 && isSafe(currentSize)) {
            return completeBinaryTree[currentSize];
        }
        return null;
    }

    private void swap(int i, int j) {
        Integer tmp = completeBinaryTree[i];
        completeBinaryTree[i] = completeBinaryTree[j];
        completeBinaryTree[j] = tmp;
    }

    private void siftUp(int idx) {
        while (idx > 0 &&
                getParent(idx) != null &&
                completeBinaryTree[idx] != null &&
                getParent(idx) < completeBinaryTree[idx]) {
            swap(parentIndex(idx), idx);
            idx = parentIndex(idx);
        }
    }

    private void siftDown(int idx) {
        int maxIndex = idx;
        Integer leftChild = getLeftChild(idx);
        if (leftChild != null && leftChild > completeBinaryTree[maxIndex]) {
            maxIndex = leftChildIndex(idx);
        }
        Integer rightChild = getRightChild(idx);
        if (rightChild != null && rightChild > completeBinaryTree[maxIndex]) {
            maxIndex = rightChildIndex(idx);
        }
        if (idx != maxIndex) {
            swap(idx, maxIndex);
            siftDown(maxIndex);
        }
    }

    public void add(Integer value) {
        if (hasCapacity()) {
            completeBinaryTree[currentSize] = value;
            siftUp(currentSize);
            currentSize++;
        }
    }

    public void addBatch(Integer... values) {
        for (Integer x : values) {
            if (x != null) {
                add(x);
            }
        }
    }

    public Integer extractMax() {
        Integer value = completeBinaryTree[0];
        completeBinaryTree[0] = getLastLeaf();
        currentSize--;
        siftDown(0);
        return value;
    }

    public void remove(int idx) {
        if (isSafe(idx)) {
            completeBinaryTree[idx] = Integer.MAX_VALUE;
            siftUp(idx);
            extractMax();
        }
    }

    public void changePriority(int idx, Integer value) {
        if (isSafe(idx)) {
            Integer tmp = completeBinaryTree[idx];
            completeBinaryTree[idx] = value;
            if (value > tmp) {
                siftUp(idx);
            } else {
                siftDown(idx);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (Integer x : completeBinaryTree) {
            if (x != null) {
                b.append(x).append(" ");
            }
        }
        return b.toString();
    }

    public void printArray() {
        System.out.println(Arrays.toString(completeBinaryTree));
    }

    public void print() {
        System.out.println(this);
    }

    public static void main(String[] args) {
        Heap heap = new Heap(13);
        heap.print();
        heap.add(42);
        heap.print();

        heap.addBatch(29, 18, 14, 7, 18, 12, 11, 5);

        heap.print();

        System.out.println(heap.extractMax());

        heap.print();

        // heap.printArray();
    }

}
