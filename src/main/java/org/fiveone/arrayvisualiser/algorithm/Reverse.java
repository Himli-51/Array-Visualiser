package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;

public class Reverse extends Algorithm {
    public Reverse(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        for (int i = 0; i < ArraySortVisualiser.ARRAY_LENGTH / 2; i++) {
            swap(i, ArraySortVisualiser.ARRAY_LENGTH - 1 - i);
            visualiser.update(1);
        }
    }

    @Override
    public String getName() { return "Reverse"; }
}
