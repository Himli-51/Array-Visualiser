package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class GnomeSort extends Algorithm {
    private static final int RATE = 12;

    public GnomeSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        int i = 0;
        byte updateFrame = 0;
        while (i < visualiser.ARRAY_LENGTH - 1) {
            if (visualiser.array[i] >  visualiser.array[i + 1]) {
                swap(i, i + 1);
                i += (i > 0) ? -1 : 1;
            } else {
                visualiser.elementColors[i] = new Color(255, 60, 0);
                visualiser.elementColors[i + 1] = new Color(255, 60, 0);
                i++;
            }
            updateFrame++;
            updateFrame %= RATE;
            if (updateFrame == 0) {
                visualiser.update(1);
            }
        }
        visualiser.update(0);
    }

    @Override
    public String getName() { return "Gnome Sort"; }
}
