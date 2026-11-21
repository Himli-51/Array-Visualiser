package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class Check extends Algorithm {
    public Check(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        for (int i = 0; i < ArraySortVisualiser.ARRAY_LENGTH - 1; i++) {
            if (visualiser.array[i] > visualiser.array[i + 1]) {
                System.out.println(visualiser.sortName + ": Array is not sorted!");
                for (int j = 0; j < ArraySortVisualiser.ARRAY_LENGTH; j++) {
                    visualiser.elementColors[j] = Color.RED;
                }
                visualiser.update(500);
                return;
            }
            for (int j = 0; j < ArraySortVisualiser.ARRAY_LENGTH; j++) {
                visualiser.elementColors[j] = (j <= i + 1) ? Color.GREEN : Color.WHITE;
            }
            visualiser.update(1);
        }
        visualiser.update(0);
        System.out.println(visualiser.sortName + ": Array is sorted!");
    }

    @Override
    public String getName() { return "Check"; }
}
