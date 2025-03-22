package com.andreat.data_structures;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MaxHeap <T extends Comparable<T>> {
    private ArrayList<T> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i){
        return 2 * i + 1;
    }

    private int rightChild(int i){
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(T element) {
        heap.add(element);
        int current = heap.size() - 1;

        while (current > 0 && heap.get(current).compareTo(heap.get(parent(current))) > 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    private void heapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int largest = i;

        if(left < heap.size() && heap.get(left).compareTo(heap.get(largest)) > 0) {
            largest = left;
        }

        if(right < heap.size() && heap.get(right).compareTo(heap.get(largest)) > 0) {
            largest = right;
        }

        if(largest != i) {
            swap(i, largest);
            heapify(largest);
        }
    }

    public T extractMax(){
        if(heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        T max = heap.get(0);

        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        heapify(0);

        return max;
    }

    public T peek(){
        if(heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap.get(0);
    }

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return heap.isEmpty();
    }

    @Override
    public String toString() {
        return heap.toString();
    }









}
