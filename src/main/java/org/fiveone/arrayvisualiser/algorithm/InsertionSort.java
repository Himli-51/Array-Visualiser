package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class InsertionSort extends Algorithm {
    private static final int RATE = 4;

    public InsertionSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        byte updateFrame = 0;
        for (int i = 1; i < visualiser.ARRAY_LENGTH; i++) {
            int j = i;
            while (j > 0 && visualiser.array[j] < visualiser.array[j - 1]) {
                swap(j, j - 1);
                visualiser.elementColors[i] = Color.GREEN;
                j--;
                updateFrame++;
                updateFrame %= RATE;
                if (updateFrame == 0) {
                    visualiser.update(1);
                }
            }
        }
        visualiser.update(0);
    }

    @Override
    public String getName() { return "Insertion Sort"; }
}
