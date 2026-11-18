package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.util.Random;

public class Shuffle extends Algorithm {
    private static final int DELAY = 1;
    private Random random;

    public Shuffle(ArraySortVisualiser visualiser) {
        super(visualiser);
        random = new Random();
    }

    @Override
    public void start() {
        for (int i = visualiser.ARRAY_LENGTH - 1; i > 0; i--) {
            swap(i, random.nextInt(i+1));
            visualiser.update(DELAY);
        }
        visualiser.update(0);
    }

    @Override
    public String getName() { return "Shuffle"; }
}
