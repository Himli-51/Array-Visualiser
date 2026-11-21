package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class BubbleSort extends Algorithm {
    private static final int RATE = 8;

    public BubbleSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        byte updateFrame = 0;
        int endIndex = ArraySortVisualiser.ARRAY_LENGTH - 2;
        while (endIndex > 1) {
            int newEndIndex = 0;
            for (int i = 0; i < endIndex; i++) {
                if (endIndex < ArraySortVisualiser.ARRAY_LENGTH) visualiser.elementColors[endIndex + 1] = Color.GREEN;
                if (visualiser.array[i] > visualiser.array[i + 1]) {
                    swap(i, i + 1);
                    newEndIndex = i;
                } else {
                    visualiser.elementColors[i] = new Color(255, 60, 0);
                    visualiser.elementColors[i + 1] = new Color(255, 60, 0);
                }
                updateFrame++;
                updateFrame %= RATE;
                if (updateFrame == 0) {
                    visualiser.update(1);
                }
            }
            endIndex = newEndIndex;
        }
        visualiser.update(0);
    }

    @Override
    public String getName() { return "Bubble Sort"; }
}
