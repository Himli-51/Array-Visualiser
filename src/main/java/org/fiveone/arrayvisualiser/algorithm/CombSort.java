package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class CombSort extends Algorithm {
    private static final int RATE = 2;
    public CombSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        byte updateFrame = 0;
        boolean sorted = false;
        for (int gap = (int)(ArraySortVisualiser.ARRAY_LENGTH / 1.3); !sorted; gap /= 1.3) {
            if (gap <= 1) {
                gap = 1;
                sorted = true;
            } else if (gap == 9 || gap == 11) {
                gap = 11;
            }

            for (int i = 0; i + gap < ArraySortVisualiser.ARRAY_LENGTH; i++) {
                if (visualiser.array[i] > visualiser.array[i + gap]) {
                    swap(i, i + gap);
                    sorted = false;
                } else {
                    visualiser.elementColors[i] = new Color(255, 60, 0);
                    visualiser.elementColors[i+ gap] = new Color(255, 60, 0);
                }
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
    public String getName() { return "Comb Sort"; }
}
