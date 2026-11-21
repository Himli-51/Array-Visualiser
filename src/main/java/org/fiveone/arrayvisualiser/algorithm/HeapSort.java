package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;

public class HeapSort extends Algorithm {
    public HeapSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        int n = ArraySortVisualiser.ARRAY_LENGTH;
    
        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(visualiser.array, n, i);
        }
    
        // Heap sort
        for (int i = n - 1; i > 0; i--) {
            swap(0, i); // Move current root to end
            visualiser.update(1);
            heapify(visualiser.array, i, 0); // Heapify reduced heap
        }
    
        visualiser.update(0); // Final update after sorting
    }
    
    private void heapify(int[] array, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child
    
        // If left child is larger than root
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }
    
        // If right child is larger than largest so far
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }
    
        // If largest is not root
        if (largest != i) {
            swap(i, largest); // Swap root and largest
            visualiser.update(1);
            heapify(array, n, largest); // Recursively heapify the affected sub-tree
        }
    }

    @Override
    public String getName() { return "Heap Sort"; }
}
