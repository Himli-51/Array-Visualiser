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
        boolean isSolved;
        for (int i = 1; i < visualiser.ARRAY_LENGTH; i++) {
            isSolved = true;
            for (int j = 0; j < visualiser.ARRAY_LENGTH - i; j++) {
                if (i > 1) {
                    visualiser.elementColors[visualiser.ARRAY_LENGTH - i + 1] = Color.GREEN;
                }
                if (visualiser.array[j] > visualiser.array[j + 1]) {
                    swap(j, j + 1);
                    isSolved = false;
                } else {
                    visualiser.elementColors[j] = new Color(255, 60, 0);
                    visualiser.elementColors[j+ 1] = new Color(255, 60, 0);
                }
                updateFrame++;
                updateFrame %= RATE;
                if (updateFrame == 0) {
                    visualiser.update(1);
                }
            }
            if (isSolved) {
                break;
            }
        }
        visualiser.update(0);
    }

    @Override
    public String getName() { return "Bubble Sort"; }
}
