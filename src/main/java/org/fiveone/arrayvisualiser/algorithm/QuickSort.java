package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class QuickSort extends Algorithm{
    public QuickSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        quickSort(visualiser.array, 0, ArraySortVisualiser.ARRAY_LENGTH - 1);
        visualiser.update(0); // Final update after sorting
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j); // Swap elements and color them red
                visualiser.update(1);
            } else {
                visualiser.elementColors[j] = new Color(255, 60, 0); // Color for comparison
                visualiser.update(1);
            }
        }
        swap(i + 1, high); // Swap pivot to its correct position
        visualiser.update(1);
        return i + 1;
    }

    @Override
    public String getName() { return  "Quick Sort"; }
}
