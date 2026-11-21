package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class ShellSort extends Algorithm {
    public ShellSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        for (int gap = ArraySortVisualiser.ARRAY_LENGTH / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < ArraySortVisualiser.ARRAY_LENGTH; i++) {
                int j = i;
                while (j >= gap && visualiser.array[j] < visualiser.array[j - gap]) {
                    swap(j, j - gap);
                    visualiser.elementColors[i] = Color.GREEN;
                    j -= gap;
                    visualiser.update(1);
                }
            }
        }
        visualiser.update(0);
    }

    @Override
    public String getName() { return "Shell Sort"; }
}
