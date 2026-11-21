package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.util.Arrays;

public class RadixLSDSort extends Algorithm {
    private int defaultBase;

    public RadixLSDSort(ArraySortVisualiser visualiser) {
        super(visualiser);
        defaultBase = 4;
    }

    public RadixLSDSort(ArraySortVisualiser visualiser, int defaultBase) {
        super(visualiser);
        this.defaultBase = defaultBase;
    }

    @Override
    public void start() {
        int max = ArraySortVisualiser.ARRAY_LENGTH;
        for (int exp = 1; max / exp > 0; exp *= defaultBase) {
            countSort(visualiser.array, exp, defaultBase);
        }
        visualiser.update(0); // Final update after sorting
    }

    public void start(int base) {
        int max = ArraySortVisualiser.ARRAY_LENGTH;
        for (int exp = 1; max / exp > 0; exp *= base) {
            countSort(visualiser.array, exp, base);
        }
        visualiser.update(0); // Final update after sorting
    }

    private void countSort(int[] array, int exp, int base) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[base];
        Arrays.fill(count, 0);
    
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % base]++;
        }
    
        for (int i = 1; i < base; i++) {
            count[i] += count[i - 1];
        }
    
        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % base] - 1] = array[i];
            count[(array[i] / exp) % base]--;
        }
    
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            visualiser.array = array;
            visualiser.update(1);
        }
    }

    @Override
    public String getName() { return "Radix Sort (Least Significant Digit)";}
}
