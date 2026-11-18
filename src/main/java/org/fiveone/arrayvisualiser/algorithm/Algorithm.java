package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public abstract class Algorithm {
    protected ArraySortVisualiser visualiser;

    public Algorithm(ArraySortVisualiser visualiser) {
        this.visualiser = visualiser;
    }

    protected void swap(int id1, int id2) {
        int tmp = visualiser.array[id1];
        visualiser.array[id1] = visualiser.array[id2];
        visualiser.array[id2] = tmp;

        visualiser.elementColors[id1] = Color.RED;
        visualiser.elementColors[id2] = Color.RED;
    }

    public abstract void start();
    public abstract String getName();
}
