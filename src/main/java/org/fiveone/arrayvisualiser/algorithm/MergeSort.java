package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class MergeSort extends Algorithm {
    public MergeSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        mergeSort(visualiser.array, 0, ArraySortVisualiser.ARRAY_LENGTH - 1);
        visualiser.update(0);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }
    
    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
    
        int[] L = new int[n1];
        int[] R = new int[n2];
    
        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];
    
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            visualiser.elementColors[k] = Color.RED;
            visualiser.array = array;
            visualiser.update(1);
            k++;
        }
    
        while (i < n1) {
            array[k] = L[i];
            visualiser.elementColors[k] = Color.RED;
            visualiser.array = array;
            visualiser.update(1);
            i++;
            k++;
        }
    
        while (j < n2) {
            array[k] = R[j];
            visualiser.elementColors[k] = Color.RED;
            visualiser.array = array;
            visualiser.update(1);
            j++;
            k++;
        }
    }

    @Override
    public String getName() { return "Merge Sort"; }
}
