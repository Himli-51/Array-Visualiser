package org.fiveone.arrayvisualiser.algorithm;

import org.fiveone.arrayvisualiser.ArraySortVisualiser;
import java.awt.Color;

public class CocktailShakerSort extends Algorithm {
    private static final int RATE = 4;

    public CocktailShakerSort(ArraySortVisualiser visualiser) {
        super(visualiser);
    }

    @Override
    public void start() {
        byte updateFrame = 0;
        int beginIndex = 0;
        int endIndex = ArraySortVisualiser.ARRAY_LENGTH - 2;
        while (beginIndex <= endIndex) {
            int newBeginIndex = endIndex;
            int newEndIndex = beginIndex;
            for (int i = beginIndex; i < endIndex; i++) {
                if (visualiser.array[i] > visualiser.array[i + 1]) {
                    swap(i, i + 1);
                    newEndIndex = i;
                } else {
                    visualiser.elementColors[i] = new Color(255, 60, 0);
                    visualiser.elementColors[i + 1] = new Color(255, 60, 0);
                }
                if (beginIndex > 0) visualiser.elementColors[beginIndex - 1] = Color.GREEN;
                if (endIndex < ArraySortVisualiser.ARRAY_LENGTH) visualiser.elementColors[endIndex + 1] = Color.GREEN;
                updateFrame++;
                updateFrame %= RATE;
                if (updateFrame == 0) {
                    visualiser.update(1);
                }
            }
            endIndex = newEndIndex;

            for (int i = endIndex; i > beginIndex; i--) {
                if (visualiser.array[i] < visualiser.array[i - 1]) {
                    swap(i, i - 1);
                    newBeginIndex = i;
                } else {
                    visualiser.elementColors[i] = new Color(255, 60, 0);
                    visualiser.elementColors[i - 1] = new Color(255, 60, 0);
                }
                if (beginIndex > 0) visualiser.elementColors[beginIndex - 1] = Color.GREEN;
                if (endIndex < ArraySortVisualiser.ARRAY_LENGTH) visualiser.elementColors[endIndex + 1] = Color.GREEN;
                updateFrame++;
                updateFrame %= RATE;
                if (updateFrame == 0) {
                    visualiser.update(1);
                }
            }
            beginIndex = newBeginIndex;
        }
        visualiser.update(0);
    }

    @Override
    public String getName() {return "Cocktail Shaker Sort"; }
    
}
