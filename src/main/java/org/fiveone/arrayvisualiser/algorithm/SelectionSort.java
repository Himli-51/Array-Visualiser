package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class SelectionSort extends Algorithm {
    private static final int RATE = 8;

    public SelectionSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        int smallest;
        byte updateFrame = 0;
        for (int i = 0; i < visualiser.ARRAY_LENGTH - 1; i++) {
            smallest = i;
            for (int j = i; j < visualiser.ARRAY_LENGTH; j++) {
                if (visualiser.array[j] < visualiser.array[smallest]) {
                    smallest = j;
                }

                visualiser.elementColors[smallest] = new Color(255, 60, 0);
                visualiser.elementColors[j] = Color.RED;
                if (i > 0) {
                    visualiser.elementColors[i - 1] = Color.GREEN;
                }

                updateFrame++;
                updateFrame %= RATE;
                if (updateFrame == 0) {
                    visualiser.update(1);
                }
            }
            swap(smallest, i);
            visualiser.update(1);
        }
        visualiser.update(0);
    }

    @Override
    public String getName() { return "Selection Sort"; }
}
